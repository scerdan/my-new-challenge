package com.mynewchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.presentation.viewmodel.UserViewModel
import com.mynewchallenge.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainViewmodel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, viewModel: UserViewModel = hiltViewModel()) {

    val userState by viewModel.userState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllUsers()
    }

    when (userState) {
        is ResultTypes.Loading -> {
            Text(text = "Loading...", modifier = modifier)
        }

        is ResultTypes.Success -> {
            val result = (userState as ResultTypes.Success<User>).data
            result?.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    Text(
                        text = item.login,
                        modifier = modifier
                            .background(Color.Green)
                    )
                }
            }
        }

        is ResultTypes.Error -> {
            Text(
                text = "An error occurred: ${(userState as ResultTypes.Error).exception.localizedMessage}",
                modifier = modifier
            )
        }

        is ResultTypes.HttpError -> {
            Text(
                text = "HTTP error: ${(userState as ResultTypes.HttpError).exception.message}",
                modifier = modifier
            )
        }

        is ResultTypes.IOError -> {
            Text(
                text = "Network error: ${(userState as ResultTypes.IOError).exception.message}",
                modifier = modifier
            )
        }

        null -> {
            Text(text = "Fetching users...", modifier = modifier)
        }
    }
}
