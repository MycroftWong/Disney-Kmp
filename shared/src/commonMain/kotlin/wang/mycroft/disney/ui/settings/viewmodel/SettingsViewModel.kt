package wang.mycroft.disney.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import wang.mycroft.disney.data.SettingsRepository

@KoinViewModel
class SettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {

    private val _setting = MutableStateFlow(false)

    val uiState: StateFlow<UiState> = settingsRepository.darkTheme.map {
        UiState(darkTheme = it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState())

    fun submitAction(action: Action) {
        if (!_setting.compareAndSet(expect = false, update = true)) return

        Logger.d { "submitAction: $action" }

        viewModelScope.launch {
            when (action) {
                is Action.SetDarkTheme -> {
                    settingsRepository.setDarkTheme(action.darkTheme)
                }
            }
        }.invokeOnCompletion {
            _setting.update { false }
        }
    }

    sealed interface Action {
        data class SetDarkTheme(val darkTheme: Boolean) : Action
    }

    data class UiState(
        val darkTheme: Boolean = false,
    )
}