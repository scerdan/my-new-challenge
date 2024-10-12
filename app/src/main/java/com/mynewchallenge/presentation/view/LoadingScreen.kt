package com.mynewchallenge.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.presentation.navigation.Screens
import com.mynewchallenge.presentation.navigation.goTo
import com.mynewchallenge.presentation.viewmodel.UserViewModel
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(navController: NavHostController, viewModel: UserViewModel) {
    val userState by viewModel.userState.collectAsState()

    when (userState) {
        is ResultTypes.Success -> {
            LaunchedEffect(Unit) {
                delay(1500)
                goTo(Screens.HOME_SCREEN.route, navController, true)
            }
        }
        else -> {
            LaunchedEffect(Unit) {
                delay(1500)
                goTo(Screens.ERROR_SCREEN.route, navController, true)
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }

}
