package com.aminovic.loula.presentation.screens.home.discover

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aminovic.loula.data.remote.dto.track.TrackDto
import com.aminovic.loula.presentation.screens.home.discover.components.AlbumTabs
import com.aminovic.loula.presentation.screens.home.discover.components.ArtistTabs
import com.aminovic.loula.presentation.screens.home.discover.components.TrackListItem
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel(),
    navigateToPlayer: () -> Unit,
    navigateToProfile: (Int, String, String, String) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val spacing = LocalSpacing.current

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
        state.chart?.artists?.data?.let { artists ->
            ArtistTabs(
                artists = artists,
                selectedArtist = state.selectedArtist,
                onArtistSelected = { artist ->
                    navigateToProfile(
                        artist.id!!.toInt(),
                        artist.pictureMedium!!,
                        artist.name!!,
                        artist.tracklist!!
                    )
                }
            )
        }

        state.chart?.albums?.albums?.let { albums ->
            AlbumTabs(
                albums = albums,
                selectedAlbum = state.selectedAlbum,
                onAlbumSelected = { album ->
                    viewModel.onEvent(DiscoverEvents.OnSelectAlbum(album = album))
                }
            )
        }

        Spacer(Modifier.height(spacing.spaceSmall))

        Crossfade(
            targetState = state.selectedAlbum,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { album ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = CenterHorizontally
            ) {
                album?.tracks?.data?.let { tracks ->
                    items(tracks) { item: TrackDto ->
                        TrackListItem(
                            track = item,
                            onClick = {},
                            playPauseTrack = { },
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = MaterialTheme.colors.surface
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PlayerImage(
    podcastImageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(podcastImageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .sizeIn(maxWidth = 500.dp, maxHeight = 500.dp)
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.medium)
    )
}