package com.aminovic.loula.presentation.screens.player.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aminovic.loula.R


@Composable
fun PlayerButtons(
    modifier: Modifier = Modifier,
    playPause: () -> Unit,
    replay10: () -> Unit,
    forward10: () -> Unit,
    next: () -> Unit,
    previous: () -> Unit,
    playerButtonSize: Dp = 72.dp,
    sideButtonSize: Dp = 48.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            onClick = previous,
        ) {
            Icon(
                imageVector = Icons.Filled.SkipPrevious,
                modifier = Modifier.size(sideButtonSize),
                contentDescription = stringResource(id = R.string.previous)
            )
        }

        IconButton(
            onClick = replay10,
        ) {
            Icon(
                imageVector = Icons.Filled.Replay10,
                modifier = Modifier.size(sideButtonSize),
                contentDescription = stringResource(id = R.string.replay10)
            )
        }

        IconButton(
            onClick = playPause,
        ) {
            Icon(
                imageVector = Icons.Filled.PlayCircleFilled,
                modifier = Modifier.size(playerButtonSize),
                contentDescription = stringResource(id = R.string.play)
            )
        }

        IconButton(
            onClick = forward10
        ) {
            Icon(
                imageVector = Icons.Filled.Forward10,
                modifier = Modifier.size(sideButtonSize),
                contentDescription = stringResource(id = R.string.forward10)
            )
        }

        IconButton(
            onClick = next
        ) {
            Icon(
                imageVector = Icons.Filled.SkipNext,
                modifier = Modifier.size(sideButtonSize),
                contentDescription = stringResource(id = R.string.next)
            )
        }
    }
}