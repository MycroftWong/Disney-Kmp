package wang.mycroft.disney.model

import kotlinx.serialization.Serializable

@Serializable
data class DisneyResponse<T>(
    val info: PageInfo,
    val data: T
)