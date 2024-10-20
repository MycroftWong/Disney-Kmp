package wang.mycroft.disney.ui.disney.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import wang.mycroft.disney.ui.disney.screen.DisneyDetailScreen

@Serializable
data class DisneyDetail(val id: Long)

fun NavController.navigateToDisneyDetail(characterId: Long, navOptions: NavOptions? = null) =
    navigate(DisneyDetail(characterId), navOptions)

fun NavGraphBuilder.disneyDetailScreen(
    onBackClick: () -> Unit
) {
    composable<DisneyDetail> {
        DisneyDetailScreen(
            onBackClick = onBackClick,
        )
    }
}
