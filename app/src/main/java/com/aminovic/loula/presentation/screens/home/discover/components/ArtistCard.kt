package com.aminovic.loula.presentation.screens.home.discover.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@Composable
fun ArtistCard(
    text: String,
    image: String?,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Surface(
        color = Color.Transparent,
        contentColor = when {
            selected -> MaterialTheme.colors.primary
            else -> MaterialTheme.colors.onSurface
        },

        modifier = modifier.width(100.dp)
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .sizeIn(maxWidth = 100.dp, maxHeight = 100.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
            )
            Text(
                text = text,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = spacing.spaceMedium,
                        vertical = spacing.spaceSmall
                    )
            )
        }
    }
}
