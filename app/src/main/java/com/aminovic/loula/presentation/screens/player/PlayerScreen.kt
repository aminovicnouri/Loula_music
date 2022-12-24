package com.aminovic.loula.presentation.screens.player

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aminovic.loula.domain.utils.asFormattedString
import com.aminovic.loula.domain.utils.convertToProgress
import com.aminovic.loula.presentation.screens.player.components.PlayerButtons
import com.aminovic.loula.presentation.screens.player.components.PlayerHeader
import com.aminovic.loula.presentation.screens.player.components.PlayerImage
import com.aminovic.loula.presentation.ui.theme.LocalSpacing
import com.aminovic.loula.presentation.ui.theme.MinContrastOfPrimaryVsSurface
import com.aminovic.loula.presentation.utils.DynamicThemePrimaryColorsFromImage
import com.aminovic.loula.presentation.utils.contrastAgainst
import com.aminovic.loula.presentation.utils.rememberDominantColorState
import com.aminovic.loula.presentation.utils.verticalGradientScrim
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState


@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalLifecycleComposeApi
@Composable
fun PlayerScreen(
    onBackPressed: () -> Unit,
    addToPlayList: () -> Unit,
    more: () -> Unit,
    viewModel: PlayerViewModel = hiltViewModel(),
) {

    val musicState by viewModel.musicState.collectAsStateWithLifecycle()
    val currentPosition by viewModel.currentPosition.collectAsStateWithLifecycle()

    val progress by animateFloatAsState(
        targetValue = convertToProgress(count = currentPosition, total = musicState.duration)
    )

    val spacing = LocalSpacing.current

    val context = LocalContext.current

    val surfaceColor = MaterialTheme.colors.surface
    val dominantColorState = rememberDominantColorState { color ->
        // We want a color which has sufficient contrast against the surface color
        color.contrastAgainst(surfaceColor) >= MinContrastOfPrimaryVsSurface
    }
    DynamicThemePrimaryColorsFromImage(dominantColorState) {
        val pagerState = rememberPagerState()

        LaunchedEffect(musicState.currentSong.artworkUri) {
            dominantColorState.updateColorsFromImageUrl(musicState.currentSong.artworkUri.toString())
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalGradientScrim(
                    color = MaterialTheme.colors.primary.copy(alpha = 0.38f),
                    startYPercentage = 1f,
                    endYPercentage = 0f
                )
        ) {
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .windowInsetsTopHeight(WindowInsets.statusBars)
            )
            PlayerHeader(onBackPress = onBackPressed, addToPlayList = addToPlayList, more = more)
            Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
            PlayerImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                trackImageUrl = musicState.currentSong.artworkUri,
            )
            Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
            Text(
                text = musicState.currentSong.title,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceSmall)
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Text(
                text = musicState.currentSong.album,
                style = MaterialTheme.typography.subtitle2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceSmall)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
            Slider(
                modifier = Modifier.padding(horizontal = spacing.spaceMedium),
                value = progress,
                onValueChange = { }
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            ) {
                Text(text = currentPosition.asFormattedString())
                Spacer(modifier = Modifier.weight(1f))
                Text(musicState.duration.asFormattedString())
            }
            Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
            PlayerButtons(
                modifier = Modifier.fillMaxWidth(),
                playWhenReady = musicState.playWhenReady,
                play = { viewModel.onEvent(PlayerEvent.Play) },
                pause = { viewModel.onEvent(PlayerEvent.Pause) },
                replay10 = { },
                forward10 = { /*TODO*/ },
                next = { viewModel.onEvent(PlayerEvent.SkipNext) },
                previous = { viewModel.onEvent(PlayerEvent.SkipPrevious) }
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))
        }
    }
}
