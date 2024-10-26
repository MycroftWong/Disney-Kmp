package wang.mycroft.disney.ui.settings.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import disney_kmp.shared.generated.resources.Res
import disney_kmp.shared.generated.resources.dark_theme
import disney_kmp.shared.generated.resources.settings
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import wang.mycroft.disney.ui.settings.viewmodel.SettingsViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
internal fun SettingsScreen(
    settingsViewModel: SettingsViewModel = koinNavViewModel(),
    onBackClick: () -> Unit,
) {
    val state by settingsViewModel.uiState.collectAsStateWithLifecycle()

    SettingsContent(
        state = state,
        onBackClick = onBackClick,
        onAction = settingsViewModel::submitAction,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsContent(
    state: SettingsViewModel.UiState,
    onBackClick: () -> Unit,
    onAction: (SettingsViewModel.Action) -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(text = stringResource(Res.string.settings))
            }
        )

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(60.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(Res.string.dark_theme))
                Spacer(Modifier.weight(1f))
                Switch(
                    checked = state.darkTheme,
                    onCheckedChange = { onAction(SettingsViewModel.Action.SetDarkTheme(it)) }
                )
            }
        }

        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
    }
}
