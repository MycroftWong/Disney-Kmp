package wang.mycroft.disney

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import org.koin.compose.KoinApplication
import wang.mycroft.disney.di.CoreModule
import wang.mycroft.disney.di.ViewModelModule
import wang.mycroft.disney.ui.disney.screen.DisneyScreen
import wang.mycroft.disney.ui.theme.DisneyTheme

@Composable
fun App(applicationContext: ApplicationContext) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }
    KoinApplication(application = {
        modules(CoreModule(applicationContext), ViewModelModule())
    }) {
        DisneyTheme {
            /*val service = remember {
                ConferenceService(context, apiEndpoint)
            }*/

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                NavHost()
                DisneyScreen {}
//                MainScreen(service)
            }
        }
    }
}
