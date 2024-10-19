package wang.mycroft.disney.ui.disney.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.*
import wang.mycroft.disney.data.DisneyCharacter
import wang.mycroft.disney.data.DisneyCharacterQueries
import wang.mycroft.disney.data.FavoriteCharacterQueries
import wang.mycroft.disney.ui.disney.navigation.DisneyDetail

class DisneyDetailViewModel(
    private val disneyCharacterQueries: DisneyCharacterQueries,
    private val favoriteCharacterQueries: FavoriteCharacterQueries,
    private val handle: SavedStateHandle,
) : ViewModel() {
    private val disneyDetail: DisneyDetail = handle.toRoute()

    val uiState: StateFlow<UiState> = combine(
        disneyCharacterQueries.selectById(disneyDetail.id).asFlow().map { it.executeAsOneOrNull() },
        favoriteCharacterQueries.selectById(disneyDetail.id).asFlow()
            .map { it.executeAsOneOrNull() }
    ) { character, favoriteCharacter ->
        UiState(character, favoriteCharacter != null)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState(character = null))

    data class UiState(
        val character: DisneyCharacter?,
        val favorite: Boolean = false,
    )
}
