package wang.mycroft.disney.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import wang.mycroft.disney.ApplicationContext
import wang.mycroft.disney.db.entity.DisneyCharacter
import wang.mycroft.disney.db.entity.FavoriteCharacter

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

@Database(entities = [DisneyCharacter::class, FavoriteCharacter::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun disneyCharacterDao(): DisneyCharacterDao
    abstract fun favoriteCharacterDao(): FavoriteCharacterDao
}

internal const val dbFileName = "disneykmp.db"

fun ApplicationContext.createRoomDatabase(): AppDatabase {
    return getDatabaseBuilder()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

expect fun ApplicationContext.getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
