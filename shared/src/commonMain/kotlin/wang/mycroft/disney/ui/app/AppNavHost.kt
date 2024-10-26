package wang.mycroft.disney.ui.app

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import wang.mycroft.disney.ui.disney.navigation.Disney
import wang.mycroft.disney.ui.disney.navigation.disneyDetailScreen
import wang.mycroft.disney.ui.disney.navigation.disneyScreen
import wang.mycroft.disney.ui.disney.navigation.navigateToDisneyDetail
import wang.mycroft.disney.ui.settings.navigation.settingsScreen

@Composable
fun AppNavHost(appNavController: AppNavController) {
    val navController = appNavController.navController
    NavHost(
        navController = navController,
        startDestination = Disney,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        disneyScreen(
            onBackClick = {},
            onDisneyCharacterClick = navController::navigateToDisneyDetail
        )

        settingsScreen(onBackClick = {})

        disneyDetailScreen(onBackClick = navController::popBackStack)
    }

}