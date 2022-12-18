package com.aminovic.loula.presentation.screens.home

sealed class HomeEvent {
    data class OnSelectTab(val category: HomeCategory) : HomeEvent()
}
