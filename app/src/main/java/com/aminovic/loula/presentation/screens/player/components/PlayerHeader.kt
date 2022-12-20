package com.aminovic.loula.presentation.screens.player.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.aminovic.loula.R
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@Composable
fun PlayerHeader(
    onBackPress: () -> Unit,
    addToPlayList: () -> Unit,
    more: () -> Unit,
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackPress,

            ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back_button)
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = addToPlayList,
        ) {
            Icon(
                imageVector = Icons.Default.PlaylistAdd,
                contentDescription = stringResource(id = R.string.add)
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceMedium))

        IconButton(
            onClick = more,
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(id = R.string.more)
            )
        }

    }
}

@Preview
@Composable
fun PreviewBar() {
    PlayerHeader(onBackPress = { /*TODO*/ }, addToPlayList = { /*TODO*/ }) {

    }
}