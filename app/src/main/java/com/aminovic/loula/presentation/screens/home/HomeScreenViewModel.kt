package com.aminovic.loula.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.loula.domain.repository.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


//
// Created by Mohamed El Amine Nouri on 17/12/2022.
// Copyright (c) 2021 Track24. All rights reserved.
//

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val musicRepository: MusicRepository
): ViewModel() {

    init {
        viewModelScope.launch {
            musicRepository.getGenres().genres.forEach { genre ->
                Log.d("hhhhhhhh","${genre.name}")
            }
        }
    }

}