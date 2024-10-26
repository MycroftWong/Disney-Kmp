package wang.mycroft.disney.ui.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import wang.mycroft.disney.ui.settings.screen.SettingsScreen

@Serializable
data object Settings

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    navigate(Settings, navOptions)
}

fun NavGraphBuilder.settingsScreen(onBackClick: () -> Unit) {
    composable<Settings> {
        SettingsScreen(onBackClick = onBackClick)
    }
}
