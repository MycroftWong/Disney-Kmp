package wang.mycroft.disney.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import wang.mycroft.disney.api.ApiService
import wang.mycroft.disney.api.ApiServiceImpl

@Module
class NetworkModule {

    @Single
    fun json(): Json = Json {
        prettyPrint = false
        ignoreUnknownKeys = true
    }

    @Single
    fun httpClient(json: Json): HttpClient {
        return HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(json)
            }
        }
    }

    @Single
    fun apiService(httpClient: HttpClient): ApiService {
        return ApiServiceImpl(httpClient)
    }
}