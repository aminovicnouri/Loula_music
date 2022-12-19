package com.aminovic.loula.presentation.screens.home.discover.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aminovic.loula.data.remote.dto.genre.GenreDto
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@Composable
fun GenresTabs(
    genres: List<GenreDto>,
    selectedGenre: GenreDto?,
    onGenreSelected: (GenreDto) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = genres.indexOfFirst { it == selectedGenre }
    val spacing = LocalSpacing.current
    val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        divider = {},
        edgePadding = spacing.spaceMediumLarge,
        indicator = emptyTabIndicator,
        modifier = modifier
    ) {
        genres.forEachIndexed { index, genre ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onGenreSelected(genre) }) {
                ChoiceChipContent(
                    text = genre.name ?: "Unknown",
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