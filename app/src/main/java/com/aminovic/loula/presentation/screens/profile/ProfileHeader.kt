package com.aminovic.loula.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aminovic.loula.R
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@Composable
fun ProfileHeader(
    visibility: Float,
    firstItemTranslationY: Float,
    artistImageUrl: String,
    nbFan: Int?,
    artistName: String,
    nbAlbum: Int?,
    onBackPress: () -> Unit

) {
    val spacing = LocalSpacing.current
    ConstraintLayout(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
            .graphicsLayer {
                alpha = 1f - visibility
                translationY = firstItemTranslationY
            }
    ) {
        val (
            image, name, followers, plays, gradient, statusBar
        ) = createRefs()

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(artistImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .constrainAs(image) {
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom,
                    )
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    height = Dimension.matchParent
                    width = Dimension.matchParent
                }
        )

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.surface.copy(alpha = 0.5f),
                            Color.Transparent,
                            MaterialTheme.colors.surface.copy(alpha = 0.5f),
                        )
                    )
                )
                .constrainAs(gradient) {
                    height = Dimension.matchParent
                    width = Dimension.matchParent
                }
        )
        Spacer(
            Modifier
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .constrainAs(statusBar) {
                    top.linkTo(parent.top)
                }
        )
        nbFan?.let { fans ->
            Text(
                text = stringResource(
                    id = R.string.followers,
                    if (fans < 1000) fans else "${fans / 1000}K"
                ),
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.constrainAs(followers) {
                    start.linkTo(parent.start, spacing.spaceMedium)
                    bottom.linkTo(parent.bottom, spacing.spaceMedium)
                    height = Dimension.preferredWrapContent
                    width = Dimension.preferredWrapContent
                }
            )
        }

        Text(
            text = artistName,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.constrainAs(name) {
                start.linkTo(parent.start, spacing.spaceMedium)
                bottom.linkTo(followers.top, spacing.spaceSmall)
                height = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        )
        nbAlbum?.let { nbAlbums ->
            Text(
                text = stringResource(id = R.string.nbAlbum, nbAlbums),
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.constrainAs(plays) {
                    centerVerticallyTo(followers)
                    start.linkTo(followers.end, spacing.spaceMedium)
                }
            )
        }
    }
}