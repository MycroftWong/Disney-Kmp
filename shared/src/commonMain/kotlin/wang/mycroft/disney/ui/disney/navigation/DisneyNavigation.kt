package wang.mycroft.disney.ui.disney.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import wang.mycroft.disney.ui.disney.screen.DisneyScreen

@Serializable
data object Disney

fun NavGraphBuilder.disneyScreen(
    onBackClick: () -> Unit,
    onDisneyCharacterClick: (Long) -> Unit
) {
    composable<Disney> {
        DisneyScreen(navigateToDetail = onDisneyCharacterClick)
    }
}
