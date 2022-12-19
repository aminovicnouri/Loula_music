package com.aminovic.loula.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.loula.domain.repository.MusicRepository
import com.aminovic.loula.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


//
// Created by Mohamed El Amine Nouri on 19/12/2022.
//

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MusicRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())

    val state: StateFlow<ProfileState>
        get() = _state


    fun getArtist(id: Int) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            repository.getArtist(id).let { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            artist = result.data
                        )
                        getArtistTracks(id)
                    }
                }
            }
        }
    }


    fun getArtistTracks(id: Int) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            repository.getArtistTrack(id).let { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            errorMessage = null,
                            tracks = result.data?.data ?: emptyList()
                        )
                    }
                }
            }
        }
    }

}