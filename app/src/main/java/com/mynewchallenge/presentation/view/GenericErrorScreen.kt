package com.mynewchallenge.presentation.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mynewchallenge.R
import com.mynewchallenge.domain.repository.TokenRepository
import com.mynewchallenge.domain.repository.dataStore
import com.mynewchallenge.presentation.navigation.Screens
import com.mynewchallenge.presentation.navigation.goTo
import com.mynewchallenge.presentation.viewmodel.UserViewModel
import com.mynewchallenge.ui.theme.ColorPrimary

@Composable
fun GenericErrorScreen(
    navController: NavHostController,
    viewModel: UserViewModel,
) {
    val context = LocalContext.current
    val tokenRepository = TokenRepository(context.dataStore)
    var token by remember { mutableStateOf("") }
    var isTokenSaved by remember { mutableStateOf(false) }
    val url = "https://github.com/settings/personal-access-tokens/"

    LaunchedEffect(isTokenSaved) {
        if (isTokenSaved) {
            tokenRepository.saveToken(token)
            goTo(Screens.LOADING_SCREEN.route, navController, true)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Oh No!",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.SansSerif
        )

        Box(
            modifier = Modifier
                .height(250.dp)
                .width(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.generic_icon),
                contentDescription = "Error icon",
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = "Something seems to have gone wrong",
            fontSize = 16.sp, // Ajustado
            fontFamily = FontFamily.SansSerif
        )
        Text(
            text = "Create new Access Token",
            fontFamily = FontFamily.SansSerif,
            fontSize = 14.sp,
            color = ColorPrimary,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )
        Spacer(modifier = Modifier.height(30.dp))

        tokenInputField(token) { newToken ->
            token = newToken
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA7DDF2)),
            onClick = {
                isTokenSaved = true
            }
        ) {
            Text("Enter", fontSize = 16.sp)
        }
    }
}

@Composable
fun tokenInputField(
    token: String,
    onTokenChanged: (String) -> Unit
) {
    val clipboardManager = LocalClipboardManager.current

    OutlinedTextField(
        value = token,
        onValueChange = {
            onTokenChanged(it)
        },
        label = {
            Text(
                "Paste Access Token",
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp),
        trailingIcon = {
            IconButton(onClick = {
                val clipboardText = clipboardManager.getText()?.text?.replace("\\s+".toRegex(), "")
                if (clipboardText != null) {
                    onTokenChanged(clipboardText)
                }
            }) {
                Icon(
                    modifier = Modifier.fillMaxSize(1f),
                    painter = painterResource(id = R.drawable.paste_icon),
                    contentDescription = "Paste",
                    tint = Color(0xFFA7DDF2)
                )
            }
        }
    )
}

