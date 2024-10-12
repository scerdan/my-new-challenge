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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.model.UserItem
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.presentation.navigation.Screens
import com.mynewchallenge.presentation.navigation.goTo
import com.mynewchallenge.presentation.viewmodel.UserViewModel
import com.mynewchallenge.ui.theme.ColorBackground

@Composable
fun HomeScreen(navController: NavHostController, viewModel: UserViewModel = hiltViewModel()) {
    val userState by viewModel.userState.collectAsState()
    val result = (userState as ResultTypes.Success<User>).data

    Column(
        modifier = Modifier
            .padding(20.dp, 8.dp)
            .fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn {
            result?.let { users ->
                items(users.size) { index ->
                    val user = users[index]
                    UserItem(
                        user = user,
                        onClick = {
                            goTo(
                                Screens.DETAIL_SCREEN.route,
                                navController,
                                false,
                                userId = user.login
                            )
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun UserItem(user: UserItem, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = ColorBackground,

            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp, 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.avatar_url,
                contentDescription = "User Image",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = user.login, color = Color.Black, fontWeight = FontWeight.Bold)
                Text(text = user.repos_url, color = Color(0xFFEB6427), fontSize = 11.sp)
            }
        }
    }
}