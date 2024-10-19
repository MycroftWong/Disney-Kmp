package wang.mycroft.disney.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import wang.mycroft.disney.ApplicationContext
import wang.mycroft.disney.api.ApiService
import wang.mycroft.disney.api.ApiServiceImpl
import wang.mycroft.disney.data.Database
import wang.mycroft.disney.data.DisneyCharacterQueries
import wang.mycroft.disney.data.DriverFactory
import wang.mycroft.disney.data.FavoriteCharacterQueries

fun CoreModule(applicationContext: ApplicationContext) = module {
    single<HttpClient> {
//        HttpClient(CIO) {
        HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = false
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single<ApiService> {
        ApiServiceImpl(get())
    }

    single<Database> {
        val driverFactory = DriverFactory(applicationContext)
        val driver = driverFactory.createDriver()
        val database = Database(driver)
        // Do more work with the database (see below).
        database
    }

    single<DisneyCharacterQueries> {
        get<Database>().disneyCharacterQueries
    }

    single<FavoriteCharacterQueries> {
        get<Database>().favoriteCharacterQueries
    }

}