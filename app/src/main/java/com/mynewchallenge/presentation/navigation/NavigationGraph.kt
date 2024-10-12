package com.mynewchallenge.presentation.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mynewchallenge.presentation.view.DetailScreen
import com.mynewchallenge.presentation.view.GenericErrorScreen
import com.mynewchallenge.presentation.view.HomeScreen
import com.mynewchallenge.presentation.view.LoadingScreen
import com.mynewchallenge.presentation.viewmodel.UserViewModel
import com.mynewchallenge.utils.CustomTopBar

@ExperimentalMaterialApi
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val viewModel: UserViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            // Aquí se coloca la TopBar, se obtiene el título según la ruta actual
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route

            // Mostrar TopBar solo en Home y Detail
            if (currentRoute == Screens.HOME_SCREEN.route || currentRoute == Screens.DETAIL_SCREEN.route) {
                val title = when (currentRoute) {
                    Screens.HOME_SCREEN.route -> "GitHub Users"
                    Screens.DETAIL_SCREEN.route -> "User Details"
                    else -> ""
                }

                CustomTopBar(
                    title = title,
                    onBackPressed = { navController.popBackStack() }
                )
            }
        }
    ) { innerPadding ->
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
}
