package com.aminovic.loula.presentation.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.aminovic.loula.presentation.screens.home.discover.components.TrackListItem
import com.aminovic.loula.presentation.ui.theme.LocalSpacing
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    artistId: Int,
    artistImageUrl: String,
    artistName: String,
    artistTrackList: String,
    navigateToPlayer: () -> Unit,
    onBackPress: () -> Unit
) {

    val state = viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()
    val statusBarColor = MaterialTheme.colors.surface.copy(alpha = 0.5f)
    val spacing = LocalSpacing.current

    val lazyListState = rememberLazyListState()
    val visibility by remember {
        derivedStateOf {
            when {
                lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() && lazyListState.firstVisibleItemIndex == 0 -> {
                    val imageSize = lazyListState.layoutInfo.visibleItemsInfo[0].size
                    val scrollOffset = lazyListState.firstVisibleItemScrollOffset
                    scrollOffset / imageSize.toFloat()
                }
                else -> 1f
            }
        }
    }
    val firstItemTranslationY by remember {
        derivedStateOf {
            when {
                lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() &&
                        lazyListState.firstVisibleItemIndex == 0 ->
                    lazyListState.firstVisibleItemScrollOffset * .6f
                else -> 0f
            }
        }
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = false
        )
    }
    LaunchedEffect(key1 = true) {
        viewModel.getArtist(artistId)
    }
    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState
        ) {
            item {
                ProfileHeader(
                    visibility = visibility,
                    firstItemTranslationY = firstItemTranslationY,
                    artistImageUrl = artistImageUrl,
                    nbFan = state.value.artist?.nbFan,
                    artistName = artistName,
                    nbAlbum = state.value.artist?.nbAlbum,
                    onBackPress = onBackPress
                )
            }

            itemsIndexed(state.value.tracks) { idx, track ->
                TrackListItem(
                    modifier = Modifier.fillMaxWidth(),
                    track = track,
                    onClick = {},
                    playPauseTrack = { /*TODO*/ },
                    backgroundColor = MaterialTheme.colors.surface
                )
                if (idx < state.value.tracks.size - 1) {
                    Divider(color = Color.LightGray)
                }
            }
        }

        TopBarWithFadeableBackground(
            backgroundAlpha = visibility,
            text = artistName,
            onNavigationIconClicked = onBackPress
        )
    }
}