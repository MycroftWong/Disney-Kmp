package wang.mycroft.disney.ui.disney.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import wang.mycroft.disney.api.ApiService
import wang.mycroft.disney.data.DisneyCharacterQueries
import wang.mycroft.disney.data.FavoriteCharacterQueries
import wang.mycroft.disney.ui.disney.DisneyCharacterItem

class DisneyViewModel(
    private val apiService: ApiService,
    private val disneyCharacterQueries: DisneyCharacterQueries,
    private val favoriteCharacterQueries: FavoriteCharacterQueries
) : ViewModel() {

    private val _loading = MutableStateFlow(false)

    val uiState: StateFlow<UiState> = combine(
        _loading,
        disneyCharacterQueries.selectAll(),
        favoriteCharacterQueries.selectAll()
    ) { loading, characters, favoriteCharacters ->
        val favoriteIds = favoriteCharacters.filter { it.favorite == 1L }.map { it.characterId }
        UiState.Data(
            loading = loading,
            disneyCharacterItems = characters
                .map { item ->
                    DisneyCharacterItem(item, favoriteIds.contains(item.id))
                }
        )
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(1000), UiState.Loading
    )

    init {
        loadCharacters()
    }

    fun refresh() {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            val characters = apiService.loadCharacters(1)
            withContext(Dispatchers.IO) {
                characters.data.forEach { character ->
                    disneyCharacterQueries.insert(
                        character.id,
                        character.createdAt,
                        character.imageUrl,
                        character.name,
                        character.url
                    )
                }
            }
        }
    }

    fun toggleFavorite(characterId: Long) {
        viewModelScope.launch {
            _loading.value = true
            try {
                withContext(Dispatchers.IO) {
                    val favorite = favoriteCharacterQueries.selectById(characterId).firstOrNull()
                    if (favorite == null) {
                        favoriteCharacterQueries.insert(
                            characterId,
                            1,
                            Clock.System.now().toString()
                        )
                    } else {
                        favoriteCharacterQueries.delete(characterId)
                    }
                }
            } finally {
                _loading.value = false
            }
        }
    }

    sealed interface UiState {
        data object Loading : UiState
        data class Data(
            val loading: Boolean,
            val disneyCharacterItems: List<DisneyCharacterItem>
        ) : UiState
    }
}