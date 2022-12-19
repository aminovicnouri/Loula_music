package com.aminovic.loula.presentation.screens.home.discover.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aminovic.loula.data.remote.dto.artist.ArtistDto
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@Composable
fun ArtistTabs(
    artists: List<ArtistDto>,
    selectedArtist: ArtistDto?,
    onArtistSelected: (ArtistDto) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = artists.indexOfFirst { it.id == selectedArtist?.id }
    val spacing = LocalSpacing.current
    val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        divider = {},
        edgePadding = spacing.spaceMediumLarge,
        indicator = emptyTabIndicator,
        modifier = modifier
    ) {
        artists.forEachIndexed { index, artist ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onArtistSelected(artist) }) {
                ArtistCard(
                    text = artist.name ?: "Unknown",
                    image = artist.pictureBig,
                    selected = index == selectedIndex,
                    modifier = Modifier.padding(
                        horizontal = spacing.spaceExtraSmall,
                        vertical = spacing.spaceMedium
                    )
                )
            }
        }
    }
}