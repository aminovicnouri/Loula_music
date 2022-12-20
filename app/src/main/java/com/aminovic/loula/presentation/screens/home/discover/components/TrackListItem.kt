package com.aminovic.loula.presentation.screens.home.discover.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.icons.rounded.PlayCircleFilled
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aminovic.loula.R
import com.aminovic.loula.data.remote.dto.track.TrackDto
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@Composable
fun TrackListItem(
    track: TrackDto,
    onClick: (String) -> Unit,
    playPauseTrack: (String) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    ConstraintLayout(
        modifier = modifier
            .clickable { onClick(track.link!!) }
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

        Image(
            imageVector = Icons.Rounded.PlayCircleFilled,
            contentDescription = stringResource(id = R.string.play),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(LocalContentColor.current),
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false, radius = 24.dp)
                ) { playPauseTrack(track.preview!!) }
                .size(48.dp)
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
                text = "${track.duration!!.toInt() / 60}:${track.duration!!.toInt() % 60}",
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