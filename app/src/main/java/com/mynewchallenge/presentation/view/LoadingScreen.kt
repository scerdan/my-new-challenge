package com.mynewchallenge.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mynewchallenge.R
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.presentation.navigation.Screens
import com.mynewchallenge.presentation.navigation.goTo
import com.mynewchallenge.presentation.viewmodel.UserViewModel
import com.mynewchallenge.ui.theme.ColorBlack
import com.mynewchallenge.ui.theme.ColorPrimary
import com.mynewchallenge.utils.LoadComponent
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
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .height(150.dp)
                .width(150.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.generic_logo),
                contentDescription = "Git Hub icon",
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = "Checkin Access Token...",
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light
        )
        LoadComponent()
    }

}
