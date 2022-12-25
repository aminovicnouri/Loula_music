package com.aminovic.loula.presentation.components.music_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aminovic.loula.R
import com.aminovic.loula.data.remote.dto.track.TrackDto
import com.aminovic.loula.domain.utils.AppIcons
import com.aminovic.loula.domain.utils.asFormattedString
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@Composable
fun TrackListItem(
    modifier: Modifier = Modifier,
    viewModel: SongItemViewModel = hiltViewModel(),
    track: TrackDto,
    onClick: (Boolean) -> Unit,
    playPauseTrack: (Boolean, Boolean, String) -> Unit,
    backgroundColor: Color = Color.Transparent
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val musicState by viewModel.musicState.collectAsState()
    val currentPosition by viewModel.currentPosition.collectAsState()
    val isRunning = musicState.currentSong.id == track.id
    musicState.playbackState.name

    val textColor = if (isRunning)
        MaterialTheme.colors.primary
    else
        MaterialTheme.colors.onSurface

    ConstraintLayout(
        modifier = modifier
            .clickable { onClick(isRunning) }
            .background(backgroundColor)
    ) {
        val (
            divider, trackTitle, trackDuration, image, playIcon,
            titleShort, addPlaylist, overflow
        ) = createRefs()

        Divider(
            modifier = Modifier.constrainAs(divider) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
                width = Dimension.fillToConstraints
            }
        )
        AsyncImage(
            model = ImageRequest.Builder(context = context)
                .data(track.album?.coverMedium)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(MaterialTheme.shapes.medium)
                .constrainAs(image) {
                    end.linkTo(parent.end, spacing.spaceMedium)
                    top.linkTo(parent.top, spacing.spaceMedium)
                }
        )

        Text(
            text = track.title ?: "Unknown",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1,
            color = textColor,
            modifier = Modifier.constrainAs(trackTitle) {
                linkTo(
                    start = parent.start,
                    end = image.start,
                    startMargin = spacing.spaceMedium,
                    endMargin = spacing.spaceMedium,
                    bias = 0f
                )
                top.linkTo(parent.top, spacing.spaceMedium)
                height = Dimension.preferredWrapContent
                width = Dimension.preferredWrapContent
            }
        )

        val titleImageBarrier = createBottomBarrier(trackTitle, image)

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = track.titleShort!!,
                style = MaterialTheme.typography.subtitle2,
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(titleShort) {
                    linkTo(
                        start = parent.start,
                        end = image.start,
                        startMargin = spacing.spaceMedium,
                        endMargin = spacing.spaceMedium,
                        bias = 0f
                    )
                    top.linkTo(trackTitle.bottom, spacing.spaceSmall)
                    height = Dimension.preferredWrapContent
                    width = Dimension.preferredWrapContent
                }
            )
        }
        Icon(
            painter = painterResource(
                id = if (isRunning && musicState.playWhenReady)
                    AppIcons.Pause.resourceId
                else
                    AppIcons.Play.resourceId
            ),
            contentDescription = stringResource(id = R.string.play),
            tint = if (isRunning) textColor else LocalContentColor.current,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false, radius = 24.dp)
                ) {
                    playPauseTrack(isRunning, musicState.playWhenReady, track.preview!!)
                }
                .size(32.dp)
                .padding(spacing.spaceSmall)
                .semantics { role = Role.Button }
                .constrainAs(playIcon) {
                    start.linkTo(parent.start, spacing.spaceMedium)
                    top.linkTo(titleImageBarrier, spacing.spaceSmall)
                    bottom.linkTo(parent.bottom, spacing.spaceSmall)
                }
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = if (isRunning)
                    currentPosition.asFormattedString()
                else
                    track.duration!!.asFormattedString(),
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.constrainAs(trackDuration) {
                    centerVerticallyTo(playIcon)
                    linkTo(
                        start = playIcon.end,
                        startMargin = spacing.spaceMediumSmall,
                        end = addPlaylist.start,
                        endMargin = spacing.spaceMedium,
                        bias = 0f
                    )
                    width = Dimension.preferredWrapContent
                }
            )

            IconButton(
                onClick = { },
                modifier = Modifier.constrainAs(addPlaylist) {
                    end.linkTo(overflow.start)
                    centerVerticallyTo(playIcon)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.PlaylistAdd,
                    contentDescription = stringResource(id = R.string.add)
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.constrainAs(overflow) {
                    end.linkTo(parent.end, spacing.spaceSmall)
                    centerVerticallyTo(playIcon)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(id = R.string.more)
                )
            }
        }
    }
}