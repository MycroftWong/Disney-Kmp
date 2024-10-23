package wang.mycroft.disney.db

import androidx.room.Room
import androidx.room.RoomDatabase
import wang.mycroft.disney.ApplicationContext

actual fun ApplicationContext.getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = application.getDatabasePath(dbFileName)
    return Room.databaseBuilder<AppDatabase>(
        context = application,
        name = dbFile.absolutePath
    )
}