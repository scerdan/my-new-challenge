package com.mynewchallenge.presentation.navigation

import androidx.navigation.NavController

/**
 * Sealed class representing different screens in the navigation graph.
 *
 * @property route The route associated with the screen.
 */
sealed class Screens(val route: String) {
    object HOME_SCREEN : Screens("HomeScreen")
    object DETAIL_SCREEN : Screens("DetailSCreen/{userId}")
    object LOADING_SCREEN : Screens("Loading")
    object ERROR_SCREEN : Screens("GenericErrorScreen")
}

/**
 * Navigates to the specified destination using the provided NavController.
 *
 * @param direction The route or destination to navigate to.
 * @param navController The NavController instance for managing navigation.
 * @param offset A boolean indicating whether to pop the back stack before navigating.
 */
fun goTo(direction: String, navController: NavController, offset: Boolean, userId: String? = null) {
    if (offset) {
        navController.popBackStack()
    }
    val finalDirection = if (userId != null) "DetailSCreen/$userId" else direction
    navController.navigate(finalDirection)
}
