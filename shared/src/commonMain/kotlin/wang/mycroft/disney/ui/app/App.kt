package wang.mycroft.disney.ui.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.NetworkFetcher
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import coil3.svg.SvgDecoder
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import wang.mycroft.disney.ApplicationContext
import wang.mycroft.disney.data.SettingsRepository
import wang.mycroft.disney.di.appModule
import wang.mycroft.disney.ui.theme.DisneyTheme
import wang.mycroft.disney.ui.welcome.WelcomeScreen
import kotlin.time.Duration.Companion.seconds

@Composable
fun App(applicationContext: ApplicationContext) {
    KoinApplication(application = {
        modules(appModule(applicationContext))
    }) {
        val networkFetcherFactory: NetworkFetcher.Factory = koinInject()
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(networkFetcherFactory)
                    add(SvgDecoder.Factory())
                }
                .crossfade(true)
                .build()
        }

        val appNavController = rememberAppNavController()

        var showWelcome by rememberSaveable {
            mutableStateOf(true)
        }
        LaunchedEffect(Unit) {
            delay(2.seconds)
            showWelcome = false
        }

        val showBottomBar = appNavController.currentTopLevelDestination != null

        val settingsRepository: SettingsRepository = koinInject()

        val darkTheme by settingsRepository.darkTheme.collectAsState(initial = false)

        DisneyTheme(darkTheme = darkTheme) {
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
                    Box(
                        modifier = Modifier
                            .weight(1F)
                            .consumeWindowInsets(WindowInsets(0))
                    ) {
                        if (showWelcome) {
                            WelcomeScreen()
                        } else {
                            AppNavHost(appNavController)
                        }
                    }

                    AnimatedVisibility(
                        showBottomBar,
                        enter = EnterTransition.None,
                        exit = shrinkVertically(animationSpec = tween(200)),
                    ) {
                        BottomAppBar(
                            modifier = Modifier.fillMaxWidth(),
                            actions = {
                                TextButton(
                                    onClick = {
                                        appNavController.navigateToTopLevelDestination(
                                            TopLevelDestination.Home
                                        )
                                    },
                                    modifier = Modifier.weight(1F),
                                ) {
                                    Text("Home")
                                }
                                TextButton(
                                    onClick = {
                                        appNavController.navigateToTopLevelDestination(
                                            TopLevelDestination.Settings
                                        )
                                    },
                                    modifier = Modifier.weight(1F),
                                ) {
                                    Text("Settings")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
