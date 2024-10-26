package wang.mycroft.disney.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import wang.mycroft.disney.ApplicationContext

internal actual fun ApplicationContext.dataStore(): DataStore<Preferences> {
    return createDataStore(
        producePath = {
            application.filesDir.resolve(dataStoreFileName).absolutePath
        }
    )
}