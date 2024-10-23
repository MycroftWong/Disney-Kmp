package wang.mycroft.disney

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import org.koin.compose.KoinApplication
import wang.mycroft.disney.di.appModule
import wang.mycroft.disney.ui.disney.navigation.Disney
import wang.mycroft.disney.ui.disney.navigation.disneyDetailScreen
import wang.mycroft.disney.ui.disney.navigation.disneyScreen
import wang.mycroft.disney.ui.disney.navigation.navigateToDisneyDetail
import wang.mycroft.disney.ui.theme.DisneyTheme

@Composable
fun App(applicationContext: ApplicationContext) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }
    KoinApplication(application = {
        modules(appModule(applicationContext))
    }) {
        val navController = rememberNavController()

        DisneyTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0),
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .consumeWindowInsets(padding)
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal,
                            ),
                        ),
                ) {
                    Box(modifier = Modifier.consumeWindowInsets(WindowInsets(0))) {
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

                            disneyDetailScreen(onBackClick = navController::popBackStack)
                        }
                    }
                }
            }
        }
    }
}
