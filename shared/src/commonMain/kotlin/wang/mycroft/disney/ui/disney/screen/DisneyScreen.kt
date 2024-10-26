package wang.mycroft.disney.ui.disney.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import wang.mycroft.disney.ui.disney.DisneyCharacterItem
import wang.mycroft.disney.ui.disney.viewmodel.DisneyViewModel

@Composable
internal fun DisneyScreen(
    disneyViewModel: DisneyViewModel = koinViewModel(),
    navigateToDetail: (Long) -> Unit,
) {
    val state by disneyViewModel.uiState.collectAsState()
    DisneyContent(
        state = state,
        onRefresh = disneyViewModel::refresh,
        navigateToDetail = navigateToDetail,
        onFavoriteClick = disneyViewModel::toggleFavorite,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DisneyContent(
    state: DisneyViewModel.UiState,
    onRefresh: () -> Unit,
    navigateToDetail: (Long) -> Unit,
    onFavoriteClick: (Long) -> Unit
) {

    Column(Modifier.fillMaxSize()) {

        CenterAlignedTopAppBar(
            title = { Text("Disney") },
            windowInsets = WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
        )

        when (state) {
            DisneyViewModel.UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Loading characters...")
                }
            }

            is DisneyViewModel.UiState.Data -> {
                val refreshState = rememberPullToRefreshState()

                val disneyCharacterItems = state.disneyCharacterItems

                Box(
                    modifier = Modifier.fillMaxSize()
                        .pullToRefresh(
                            isRefreshing = state.loading,
                            state = refreshState,
                            onRefresh = onRefresh,
                        )
                ) {
                    LazyColumn(Modifier.fillMaxSize()) {
                        if (disneyCharacterItems.isNotEmpty()) {
                            items(disneyCharacterItems) { item ->
                                DisneyItem(
                                    item = item,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    onItemClick = navigateToDetail,
                                    onFavoriteClick = onFavoriteClick
                                )
                            }
                            item {
                                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
                            }
                        } else {
                            item {
                                Box(Modifier.fillParentMaxSize()) {
                                    Text(
                                        text = "No characters found",
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DisneyItem(
    item: DisneyCharacterItem,
    modifier: Modifier,
    onItemClick: (Long) -> Unit,
    onFavoriteClick: (Long) -> Unit
) {
    Row(modifier
        .clickable { onItemClick(item.character.id) }
        .padding(horizontal = 16.dp, vertical = 8.dp)) {
        if (item.character.imageUrl != null) {
            AsyncImage(
                model = item.character.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(Color.LightGray),
                modifier = Modifier
                    .size(width = 80.dp, height = 60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Text(
            text = item.character.name,
            modifier = Modifier
                .weight(1F)
                .align(Alignment.CenterVertically)
        )
        Switch(
            checked = item.favorite,
            onCheckedChange = { onFavoriteClick(item.character.id) },
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

