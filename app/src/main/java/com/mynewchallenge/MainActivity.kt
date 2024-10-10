package com.mynewchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.model.UserSearch
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.presentation.view.HomeScreen
import com.mynewchallenge.presentation.viewmodel.UserViewModel
import com.mynewchallenge.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApplicationTheme {
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
            HomeScreen(result)
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
