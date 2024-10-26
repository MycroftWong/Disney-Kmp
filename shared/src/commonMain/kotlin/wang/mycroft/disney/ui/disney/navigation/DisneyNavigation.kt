package wang.mycroft.disney.ui.disney.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import wang.mycroft.disney.ui.disney.screen.DisneyScreen

@Serializable
data object Disney

fun NavController.navigateToDisney(navOptions: NavOptions? = null) {
    navigate(Disney, navOptions)
}

fun NavGraphBuilder.disneyScreen(
    onBackClick: () -> Unit,
    onDisneyCharacterClick: (Long) -> Unit
) {
    composable<Disney> {
        DisneyScreen(navigateToDetail = onDisneyCharacterClick)
    }
}
