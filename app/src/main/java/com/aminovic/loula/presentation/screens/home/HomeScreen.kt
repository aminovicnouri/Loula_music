package com.aminovic.loula.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aminovic.loula.presentation.screens.home.components.HomeCategoryTab
import com.aminovic.loula.presentation.screens.home.components.HomeHeader
import com.aminovic.loula.presentation.screens.home.discover.DiscoverScreen
import com.aminovic.loula.presentation.ui.theme.LocalSpacing

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToPlayer: () -> Unit,
    navigateToProfile: (Int, String, String, String) -> Unit,
) {
    val spacing = LocalSpacing.current
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.systemBars.only(WindowInsetsSides.Horizontal)
            )
    ) {
        val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f)
        Spacer(
            Modifier
                .background(appBarColor)
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
        )
        HomeHeader(
            onSearchClick = {},
            onProfileClicked = {},
            backgroundColor = appBarColor
        )
        HomeCategoryTab(
            categories = state.categories,
            selectedCategory = state.selectedCategory,
            onCategorySelected = { category ->
                viewModel.onEvent(HomeEvent.OnSelectTab(category))
            }
        )
        when (state.selectedCategory) {
            HomeCategory.Library -> {
                // TODO
            }
            HomeCategory.Discover -> {
                DiscoverScreen(
                    navigateToPlayer = navigateToPlayer,
                    navigateToProfile = navigateToProfile,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }
    }
}