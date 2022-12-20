package com.aminovic.loula

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.aminovic.loula.presentation.screens.Screens
import com.aminovic.loula.presentation.screens.home.HomeScreen
import com.aminovic.loula.presentation.screens.player.PlayerScreen
import com.aminovic.loula.presentation.screens.profile.ProfileScreen
import com.aminovic.loula.presentation.ui.theme.LOULATheme
import com.aminovic.loula.presentation.utils.DevicePosture
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        /**
         * Flow of [DevicePosture] that emits every time there's a change in the windowLayoutInfo
         */

        setContent {
            LOULATheme {
                val navController = rememberAnimatedNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screens.Home.route
                    ) {
                        composable(route = Screens.Home.route) {
                            HomeScreen(
                                navigateToPlayer = { trackTitle, artist, duration, imageUrl, trackUrl ->
                                    navController.navigate(
                                        Screens.Player.createRoute(
                                            trackTitle,
                                            artist,
                                            duration,
                                            imageUrl,
                                            trackUrl
                                        )
                                    )
                                },
                                navigateToProfile = { id, imageUrl, name, tracksList ->
                                    navController.navigate(
                                        Screens.Profile.createRoute(
                                            artistId = id,
                                            artistName = name,
                                            artistImageUrl = imageUrl,
                                            artistTrackList = tracksList
                                        )
                                    )
                                }
                            )
                        }
                        composable(
                            route = Screens.Profile.route,
                            arguments = listOf(
                                navArgument("artistId") {
                                    type = NavType.IntType
                                },
                                navArgument("artistImageUrl") {
                                    type = NavType.StringType
                                },
                                navArgument("artistName") {
                                    type = NavType.StringType
                                },
                                navArgument("artistTrackList") {
                                    type = NavType.StringType
                                },
                            )
                        ) {
                            val id = it.arguments?.getInt("artistId")!!
                            val artistName = it.arguments?.getString("artistName")!!
                            val artistImageUrl = it.arguments?.getString("artistImageUrl")!!
                            val artistTrackList = it.arguments?.getString("artistTrackList")!!
                            ProfileScreen(
                                artistId = id,
                                artistImageUrl = artistImageUrl,
                                artistName = artistName,
                                artistTrackList = artistTrackList,
                                navigateToPlayer = { trackTitle, artist, duration, imageUrl, trackUrl ->
                                    navController.navigate(
                                        Screens.Player.createRoute(
                                            trackTitle,
                                            artist,
                                            duration,
                                            imageUrl,
                                            trackUrl
                                        )
                                    )
                                },
                                onBackPress = { navController.popBackStack() }
                            )
                        }
                        composable(
                            route = Screens.Player.route,
                            arguments = listOf(
                                navArgument("trackTitle") {
                                    type = NavType.StringType
                                },
                                navArgument("artist") {
                                    type = NavType.StringType
                                },
                                navArgument("duration") {
                                    type = NavType.IntType
                                },
                                navArgument("imageUrl") {
                                    type = NavType.StringType
                                },
                                navArgument("trackUrl") {
                                    type = NavType.StringType
                                },
                            )
                        ) {
                            val trackTitle = it.arguments?.getString("trackTitle")!!
                            val artist = it.arguments?.getString("artist")!!
                            val duration = it.arguments?.getInt("duration")!!
                            val imageUrl = it.arguments?.getString("imageUrl")!!
                            val trackUrl = it.arguments?.getString("trackUrl")!!
                            PlayerScreen(
                                trackTitle = trackTitle,
                                artist = artist,
                                duration = duration,
                                imageUrl = imageUrl,
                                trackUrl = trackUrl,
                                onBackPressed = { navController.popBackStack() },
                                addToPlayList = { /*TODO*/ },
                                more = { /*TODO*/ })
                        }
                    }
                }
            }
        }
    }
}
