package wang.mycroft.disney.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import wang.mycroft.disney.App
import wang.mycroft.disney.ApplicationContext
import wang.mycroft.disney.Greeting

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val applicationContext = ApplicationContext(
            application,
        )
        setContent {
            App(applicationContext)
        }
    }
}
