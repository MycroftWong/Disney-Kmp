package wang.mycroft.disney.di

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import wang.mycroft.disney.ApplicationContext
import wang.mycroft.disney.db.AppDatabase
import wang.mycroft.disney.db.createRoomDatabase

@Module
class DatabaseModule {
    @Single
    fun appDatabase(applicationContext: ApplicationContext): AppDatabase {
        return applicationContext.createRoomDatabase()
    }

    @Single
    fun disneyCharacterDao(appDatabase: AppDatabase) = appDatabase.disneyCharacterDao()

    @Single
    fun favoriteCharacterDao(appDatabase: AppDatabase) = appDatabase.favoriteCharacterDao()
}
