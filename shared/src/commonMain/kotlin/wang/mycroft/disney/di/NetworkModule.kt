package wang.mycroft.disney.di

import coil3.network.NetworkFetcher
import coil3.network.ktor3.KtorNetworkFetcherFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
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
        return HttpClient(DefaultHttpEngine) {
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

    @Single
    fun networkFetcherFactory(httpClient: HttpClient): NetworkFetcher.Factory {
        return KtorNetworkFetcherFactory(httpClient)
    }
}

internal expect val DefaultHttpEngine: HttpClientEngineFactory<HttpClientEngineConfig>