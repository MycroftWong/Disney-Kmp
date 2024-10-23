package wang.mycroft.disney.ui.disney.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.*
import org.koin.android.annotation.KoinViewModel
import wang.mycroft.disney.db.entity.DisneyCharacter
import wang.mycroft.disney.db.DisneyCharacterDao
import wang.mycroft.disney.db.FavoriteCharacterDao
import wang.mycroft.disney.ui.disney.navigation.DisneyDetail

@KoinViewModel
class DisneyDetailViewModel(
    private val disneyCharacterDao: DisneyCharacterDao,
    private val favoriteCharacterDao: FavoriteCharacterDao,
    private val handle: SavedStateHandle,
) : ViewModel() {

    private val disneyDetail: DisneyDetail = handle.toRoute()

    val uiState: StateFlow<UiState> = combine(
        disneyCharacterDao.selectById(disneyDetail.id),
        favoriteCharacterDao.selectById(disneyDetail.id)
    ) { character, favoriteCharacter ->
        UiState(character, favoriteCharacter != null)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState(character = null))

    data class UiState(
        val character: DisneyCharacter?,
        val favorite: Boolean = false,
    )
}
