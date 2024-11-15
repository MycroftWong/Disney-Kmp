package wang.mycroft.disney.ui.disney.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import wang.mycroft.disney.ui.disney.viewmodel.DisneyDetailViewModel

@Composable
internal fun DisneyDetailScreen(
    disneyDetailViewModel: DisneyDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state by disneyDetailViewModel.uiState.collectAsState()
    DisneyDetailContent(state, onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DisneyDetailContent(
    state: DisneyDetailViewModel.UiState,
    onBackClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text(state.character?.name ?: "Disney") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        if (state.character != null) {
            Column(
                Modifier.fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = state.character.imageUrl,
                    contentDescription = state.character.name,
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentHeight(),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
            }
        } else {
            Box(Modifier.fillMaxSize()) {
                Text(
                    text = "Character not found",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

    }
}
