package com.mynewchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
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

    // Llama a getAllUsers solo una vez al inicio
    LaunchedEffect(Unit) {
        viewModel.getAllUsers()
    }

    when (userState) {
        is ResultTypes.Loading -> {
            Text(text = "Loading...", modifier = modifier)
        }

        is ResultTypes.Success -> {
            val users = (userState as ResultTypes.Success<List<User>>).data
            if (!users.isNullOrEmpty()) {
                // Si hay usuarios, muestra el primero (como ejemplo)
                Text(text = "Hello ${users[0].name}!", modifier = modifier)
            } else {
                Text(text = "No users found", modifier = modifier)
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
