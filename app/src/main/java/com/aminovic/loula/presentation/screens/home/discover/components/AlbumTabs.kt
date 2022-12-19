package com.aminovic.loula.presentation.screens.home.discover.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aminovic.loula.data.remote.dto.album.AlbumDto
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@Composable
fun AlbumTabs(
    albums: List<AlbumDto>,
    selectedAlbum: AlbumDto?,
    onAlbumSelected: (AlbumDto) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = albums.indexOfFirst { it.id == selectedAlbum!!.id }
    val spacing = LocalSpacing.current
    val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        divider = {},
        edgePadding = spacing.spaceMediumLarge,
        indicator = emptyTabIndicator,
        modifier = modifier
    ) {
        albums.forEachIndexed { index, album ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onAlbumSelected(album) }
            ) {
                AlbumCard(
                    text = album.title ?: "Unknown",
                    image = album.coverMedium,
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