package com.aminovic.loula.presentation.screens.home.discover

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aminovic.loula.data.remote.dto.track.TrackDto
import com.aminovic.loula.presentation.components.music_item.TrackListItem
import com.aminovic.loula.presentation.screens.home.discover.components.AlbumTabs
import com.aminovic.loula.presentation.screens.home.discover.components.ArtistTabs
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@ExperimentalFoundationApi
@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel(),
    navigateToPlayer: () -> Unit,
    navigateToProfile: (Int, String, String, String) -> Unit,
) {
    val state by viewModel.state.collectAsState()
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

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(spacing.spaceSmall))

        AnimatedVisibility(
            visible = state.isLoading,
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(all = spacing.spaceLarge)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(spacing.spaceLarge),
                    color = MaterialTheme.colors.primary
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = lazyListState
        ) {
            state.chart?.artists?.data?.let { artists ->
                item {
                    ArtistTabs(
                        artists = artists,
                        selectedArtist = state.selectedArtist,
                        onArtistSelected = { artist ->
                            navigateToProfile(
                                artist.id!!,
                                artist.pictureMedium!!,
                                artist.name!!,
                                artist.tracklist!!
                            )
                        },
                        visibility = visibility
                    )
                }
            }

            state.chart?.albums?.albums?.let { albums ->
                stickyHeader {
                    AlbumTabs(
                        albums = albums,
                        selectedAlbum = state.selectedAlbum,
                        onAlbumSelected = { album ->
                            viewModel.onEvent(DiscoverEvents.OnSelectAlbum(album = album))
                        }
                    )
                }
            }

            item {
                Spacer(Modifier.height(spacing.spaceSmall))
            }

            state.selectedAlbum?.tracks?.data?.let { tracks ->
                itemsIndexed(tracks) { index: Int, item: TrackDto ->
                    TrackListItem(
                        track = item,
                        onClick = { isRunning ->
                            if (!isRunning)
                                viewModel.onEvent(
                                    DiscoverEvents.PlaySound(
                                        isRunning = false,
                                        playWhenReady = false,
                                        idx = index
                                    )
                                )
                            navigateToPlayer()
                        },
                        playPauseTrack = { isRunning, playWhenReady ->
                            viewModel.onEvent(
                                DiscoverEvents.PlaySound(
                                    isRunning,
                                    playWhenReady,
                                    index
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = MaterialTheme.colors.surface
                    )
                }
            }
        }
    }
}