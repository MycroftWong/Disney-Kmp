package wang.mycroft.disney.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import disney_kmp.shared.generated.resources.Res
import disney_kmp.shared.generated.resources.app_name
import disney_kmp.shared.generated.resources.splash_screen_bird
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(Res.drawable.splash_screen_bird),
            contentDescription = null,
            modifier = Modifier.size(240.dp),
        )
        Spacer(Modifier.height(16.dp))
        Text(stringResource(Res.string.app_name))
    }
}
