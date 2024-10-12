package com.mynewchallenge.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynewchallenge.presentation.view.DetailScreen
import com.mynewchallenge.presentation.view.GenericErrorScreen
import com.mynewchallenge.presentation.view.HomeScreen
import com.mynewchallenge.presentation.view.LoadingScreen
import com.mynewchallenge.presentation.viewmodel.UserViewModel

/**
 * Defines the navigation graph of the application using Jetpack Compose Navigation.
 *
 * @param navController The navigation controller to manage navigation within the app.
 */

@ExperimentalMaterialApi
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val viewModel: UserViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screens.LOADING_SCREEN.route) {

        composable(Screens.LOADING_SCREEN.route) {
            LoadingScreen(navController, viewModel)
        }

        composable(Screens.HOME_SCREEN.route) {
            HomeScreen(navController, viewModel)
        }

        composable(Screens.DETAIL_SCREEN.route) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            DetailScreen(navController, viewModel, userId)
        }

        composable(Screens.ERROR_SCREEN.route) {
            GenericErrorScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

