package wang.mycroft.disney.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import wang.mycroft.disney.ApplicationContext
import wang.mycroft.disney.data.SettingsRepository
import wang.mycroft.disney.data.SettingsRepositoryImpl
import wang.mycroft.disney.data.dataStore

@Module
class RepositoryModule {

    @Single
    fun dataStore(applicationContext: ApplicationContext): DataStore<Preferences> {
        return applicationContext.dataStore()
    }

    @Single
    fun settingsRepository(userPreferences: DataStore<Preferences>): SettingsRepository {
        return SettingsRepositoryImpl(userPreferences)
    }
}
