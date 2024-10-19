package wang.mycroft.disney.data

import io.ktor.client.engine.HttpClientEngine

expect class HttpClientEngineFactory {
    fun httpClientEngine(): HttpClientEngine
}