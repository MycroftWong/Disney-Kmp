package wang.mycroft.disney

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform