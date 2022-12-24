package com.aminovic.loula.presentation.screens.profile

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.aminovic.loula.presentation.components.music_item.TrackListItem
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

    val state by viewModel.state.collectAsState()
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
        viewModel.onEvent(ProfileEvent.GetArtist(artistId = artistId))
        viewModel.onEvent(ProfileEvent.InitiatePaginator(query = artistTrackList))
    }
    Box {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = spacing.spaceMedium),
            state = lazyListState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ProfileHeader(
                    visibility = visibility,
                    firstItemTranslationY = firstItemTranslationY,
                    artistImageUrl = artistImageUrl,
                    nbFan = state.artist?.nbFan,
                    artistName = artistName,
                    nbAlbum = state.artist?.nbAlbum,
                    onBackPress = onBackPress
                )
            }

            itemsIndexed(state.tracks) { idx, track ->
                TrackListItem(
                    modifier = Modifier.fillMaxWidth(),
                    track = track,
                    onClick = {
                        navigateToPlayer()
                    },
                    playPauseTrack = { isRunning, trackUrl ->
                        Log.d("hhhhhhhh", "$isRunning")
                        viewModel.onEvent(ProfileEvent.PlaySound(isRunning = isRunning, idx = idx))
                    },
                    backgroundColor = MaterialTheme.colors.surface
                )
                if (idx < state.tracks.size - 1) {
                    Divider(color = Color.LightGray)
                } else {
                    if (state.nextPage != null && !state.isLoading) {
                        viewModel.onEvent(ProfileEvent.LoadNextItems)
                    }
                }
            }
            item {
                AnimatedVisibility(
                    visible = state.isLoading,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(all = spacing.spaceLarge)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(spacing.spaceLarge),
                            color = MaterialTheme.colors.primary
                        )
                    }
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