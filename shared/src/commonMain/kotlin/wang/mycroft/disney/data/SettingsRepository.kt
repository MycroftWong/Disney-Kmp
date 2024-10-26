package wang.mycroft.disney.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SettingsRepository {
    val darkTheme: Flow<Boolean>

    suspend fun setDarkTheme(darkTheme: Boolean)
}

class SettingsRepositoryImpl(
    private val userPreferences: DataStore<Preferences>
) : SettingsRepository {

    companion object {
        private const val DEFAULT_DARK_THEME = false
    }

    private val darkThemeKey = booleanPreferencesKey("dark_theme")

    override val darkTheme: Flow<Boolean> =
        userPreferences.data.map { it[darkThemeKey] ?: DEFAULT_DARK_THEME }

    override suspend fun setDarkTheme(darkTheme: Boolean) {
        userPreferences.edit {
            it[darkThemeKey] = darkTheme
        }
    }
}
