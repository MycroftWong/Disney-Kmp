package wang.mycroft.disney.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import wang.mycroft.disney.ui.disney.navigation.navigateToDisney
import wang.mycroft.disney.ui.settings.navigation.navigateToSettings

@Composable
fun rememberAppNavController(): AppNavController {
    val navController = rememberNavController()
    return remember {
        AppNavController(navController = navController)
    }
}

class AppNavController(val navController: NavHostController) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(topLevelDestination.klazz) ?: false
            }
        }

    fun navigateToTopLevelDestination(destination: TopLevelDestination) {
        val navOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
        when (destination) {
            TopLevelDestination.Home -> navController.navigateToDisney(navOptions)
            TopLevelDestination.Settings -> navController.navigateToSettings(navOptions)
        }
    }
}
