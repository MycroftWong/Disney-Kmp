package wang.mycroft.disney.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import okio.Path.Companion.toPath
import wang.mycroft.disney.ApplicationContext

private var dataStore: DataStore<Preferences>? = null

private val lock = SynchronizedObject()

internal fun createDataStore(producePath: () -> String): DataStore<Preferences> {
    return synchronized(lock) {
        dataStore ?: PreferenceDataStoreFactory.createWithPath(produceFile = {
            producePath().toPath()
        }).also {
            dataStore = it
        }
    }
}

internal const val dataStoreFileName = "settings.preferences_pb"

internal expect fun ApplicationContext.dataStore(): DataStore<Preferences>
