package wang.mycroft.disney.ui.app

import wang.mycroft.disney.ui.disney.navigation.Disney
import kotlin.reflect.KClass

enum class TopLevelDestination(val klazz: KClass<*>) {
    Home(Disney::class), Settings(wang.mycroft.disney.ui.settings.navigation.Settings::class)
}