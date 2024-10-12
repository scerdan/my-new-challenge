package com.mynewchallenge.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mynewchallenge.R
import com.mynewchallenge.data.model.UserSearch
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.presentation.viewmodel.UserViewModel
import com.mynewchallenge.ui.theme.ColorBackground
import com.mynewchallenge.ui.theme.ColorPrimary
import com.mynewchallenge.ui.theme.ColorSecondary
import com.mynewchallenge.utils.LoadComponent

@Composable
fun DetailScreen(navController: NavHostController, viewModel: UserViewModel, userId: String?) {
    val searchState by viewModel.userSearch.collectAsState()
    val followersCount by viewModel.followersCount.collectAsState()
    val followingCount by viewModel.followingCount.collectAsState()

    LaunchedEffect(Unit) {
        if (userId != null) {
            viewModel.getSearchUser(userId)
            viewModel.fetchFollowersAndFollowing(userId)
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
                    .padding(16.dp, 14.dp),
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ColorBackground
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
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.pin),
                                    contentDescription = "Location Icon",
                                    modifier = Modifier
                                        .height(13.dp)
                                )
                                Text(
                                    text = data?.location ?: "Location not available",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 90.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    when (val result = followersCount) {
                        is ResultTypes.Success -> {
                            result.data?.let {
                                CircularBlueBox(
                                    R.drawable.follower,
                                    "Follower",
                                    it
                                )
                            }
                        }

                        is ResultTypes.Loading -> {
                            LoadComponent()
                        }

                        else -> {}
                    }

                    when (val result = followingCount) {
                        is ResultTypes.Success -> {
                            result.data?.let { CircularBlueBox(R.drawable.medal, "Following", it) }
                        }

                        is ResultTypes.Loading -> {
                            LoadComponent()
                        }

                        else -> {}

                    }
                }
                Text(
                    text = "Blog",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = data?.blog.toString(),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }

        else -> {}
    }
}

@Composable
fun CircularBlueBox(icon: Int, title: String, data: Int) {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(ColorSecondary, shape = CircleShape)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Git Hub icon",
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(10.dp)
            )
        }

        Text(
            text = "$data+",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
    }
}