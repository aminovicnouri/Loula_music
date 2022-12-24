package com.aminovic.loula.presentation.screens.player.components

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@ExperimentalMaterialApi
@Composable
fun PlayerImage(
    trackImageUrl: Uri,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier.padding(all = spacing.spaceExtraLarge)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(trackImageUrl)
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
}
