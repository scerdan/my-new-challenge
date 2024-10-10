package com.mynewchallenge.presentation.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.model.UserSearch
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.presentation.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(result: User?, viewModel: UserViewModel = hiltViewModel()) {
    val searchState by viewModel.userSearch.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    when (searchState) {
        is ResultTypes.Error -> {}
        is ResultTypes.HttpError -> {}
        is ResultTypes.IOError -> {}
        is ResultTypes.Loading -> {}
        is ResultTypes.Success -> {
            Toast.makeText(
                LocalContext.current,
                (searchState as ResultTypes.Success<UserSearch>).data?.id.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }

        null -> {}
    }


    Column(
        modifier = Modifier
            .padding(20.dp, 8.dp)
            .fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChange = {
                searchQuery = it
            },
            onSearch = {
                keyboardController?.hide()
                viewModel.getSearchUser(searchQuery)
                searchQuery = ""
            },
            active = false,
            onActiveChange = {}) {
        }

        LazyColumn {
            result?.let { users ->
                items(users.size) { index ->
                    val user = users[index]
                    Text(
                        text = user.login,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}