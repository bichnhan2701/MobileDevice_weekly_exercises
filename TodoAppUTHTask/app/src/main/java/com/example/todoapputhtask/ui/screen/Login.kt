package com.example.todoapputhtask.ui.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapputhtask.R
import com.example.todoapputhtask.ui.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Login (navController: NavController, viewModel: AuthViewModel = viewModel()) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        viewModel.handleSignInResult(task)
    }
    val isLoggedIn by viewModel.isLoggedIn.observeAsState(false)

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn && navController.currentDestination?.route == "login") {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true } // Xóa màn hình Login khỏi stack
            }
        }
    }

    Box(
    modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.size(400.dp)
        )
        Column (
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 70.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color(0xFFD5EDFF))
            ) {
                Image(
                    painter = painterResource(R.drawable.uthlogonobg),
                    contentDescription = "UTH logo",
                    modifier = Modifier.size(150.dp) .align(Alignment.Center)
                )
            }

            Text(
                text = "SmartTasks",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3991D8)
                ),
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "A simple and efficient to-do app",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF3991D8)
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(150.dp))
            Text(
                text = "Welcome",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Ready to explore? Log in to get started.",
                style = TextStyle(
                    fontSize = 18.sp
                )
            )

            Button(
                onClick = { launcher.launch(viewModel.getSignInIntent()) },
                modifier = Modifier.fillMaxWidth() .padding(16.dp)
            ) {

                Text(
                    "SIGN IN WITH GOOGLE"
                )
            }
            Spacer(modifier = Modifier.height(185.dp))
            Text(
                text = "© UTHSmartTasks"
            )
        }
    }
}