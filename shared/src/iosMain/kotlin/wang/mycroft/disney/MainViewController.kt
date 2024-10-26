package wang.mycroft.disney

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import wang.mycroft.disney.ui.app.App

fun MainViewController(): UIViewController = ComposeUIViewController(configure = {
}) {
    App(ApplicationContext())
}
