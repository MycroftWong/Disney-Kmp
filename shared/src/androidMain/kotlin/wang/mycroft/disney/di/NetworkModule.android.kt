package wang.mycroft.disney.di

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

internal actual val DefaultHttpEngine: HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp