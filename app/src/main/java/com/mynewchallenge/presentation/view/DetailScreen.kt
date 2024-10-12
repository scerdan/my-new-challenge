package com.mynewchallenge.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mynewchallenge.data.model.UserSearch
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.presentation.viewmodel.UserViewModel

@Composable
fun DetailScreen(navController: NavHostController, viewModel: UserViewModel, userId: String?) {
    val searchState by viewModel.userSearch.collectAsState()


    LaunchedEffect(Unit) {
        if (userId != null) {
            viewModel.getSearchUser(userId)
        }
    }

    when (searchState) {
        is ResultTypes.Error -> {}
        is ResultTypes.HttpError -> {}
        is ResultTypes.IOError -> {}
        is ResultTypes.Success -> {
            val data = (searchState as ResultTypes.Success<UserSearch>).data
            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clickable {},
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF0DDA6)
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp, 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = data?.avatar_url,
                            contentDescription = "User Image",
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            data?.login?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            Text(
                                text = data?.location ?: "Location not available",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "100+", style = MaterialTheme.typography.titleMedium)
                            Text(text = "Follower", style = MaterialTheme.typography.bodyMedium)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "10+", style = MaterialTheme.typography.titleMedium)
                            Text(text = "Following", style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                    // Enlace al blog
                    Text(
                        text = "Blog\n${data?.blog}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
        }

        else -> {}
    }
}