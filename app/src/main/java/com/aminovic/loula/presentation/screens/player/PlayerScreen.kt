package com.aminovic.loula.presentation.screens.player

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.aminovic.loula.presentation.screens.player.components.PlayerButtons
import com.aminovic.loula.presentation.screens.player.components.PlayerHeader
import com.aminovic.loula.presentation.screens.player.components.PlayerImage
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@Composable
fun PlayerScreen(
    trackTitle: String,
    artist: String,
    duration: Int,
    imageUrl: String,
    trackUrl: String,
    onBackPressed: () -> Unit,
    addToPlayList: () -> Unit,
    more: () -> Unit,
    viewModel: PlayerViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(
            Modifier
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
        )

        PlayerHeader(onBackPress = onBackPressed, addToPlayList = addToPlayList, more = more)
        Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
        PlayerImage(trackImageUrl = imageUrl)
        Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
        Text(
            text = trackTitle,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceSmall)
        )
        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
        Text(
            text = artist,
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceSmall)
        )
        Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
        Slider(
            modifier = Modifier.padding(horizontal = spacing.spaceMedium),
            value = 10f,
            onValueChange = { }
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)
        ) {
            Text(text = "0s")
            Spacer(modifier = Modifier.weight(1f))
            Text("$duration s")
        }
        Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
        PlayerButtons(
            modifier = Modifier.fillMaxWidth(),
            playPause = { /*TODO*/ },
            replay10 = { /*TODO*/ },
            forward10 = { /*TODO*/ },
            next = { /*TODO*/ },
            previous = { /*TODO*/ }
        )
    }

}