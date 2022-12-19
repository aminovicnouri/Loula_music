package com.aminovic.loula.presentation.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aminovic.loula.presentation.screens.home.discover.components.ArtistCard


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    artistId: Int,
    artistImageUrl: String,
    artistName: String,
    artistTrackList: String,
    navigateToPlayer: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getArtist(artistId)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ArtistCard(text = artistName, image = artistImageUrl, selected = true)

    }

}