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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import wang.mycroft.disney.ui.disney.viewmodel.DisneyDetailViewModel

@Composable
fun DisneyDetailScreen(
    viewModel: DisneyDetailViewModel,
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    Box(modifier = modifier) {
        DisneyDetailContent(Modifier.fillMaxSize(), state, onBack)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisneyDetailContent(
    modifier: Modifier = Modifier,
    state: DisneyDetailViewModel.UiState,
    onBack: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(state.character?.name ?: "Disney") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }) {
        if (state.character != null) {
            Column(modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {
                AsyncImage(
                    model = state.character.imageUrl,
                    contentDescription = state.character.name,
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    contentScale = ContentScale.FillWidth
                )
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