package com.aminovic.loula.presentation.screens

sealed class Screens(val route: String) {
    object Home: Screens(route = "home")
}
