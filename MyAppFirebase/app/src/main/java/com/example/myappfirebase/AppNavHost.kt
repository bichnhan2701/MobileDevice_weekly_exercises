package com.example.myappfirebase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myappfirebase.ui.screen.GoogleSignInScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { GoogleSignInScreen(navController) }
        composable("home/{userName}/{userEmail}") { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName")?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            } ?: "Người dùng"
            val userEmail = backStackEntry.arguments?.getString("userEmail")?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            } ?: "Không có email"
            HomeScreen(userName, userEmail)
        }
    }
}

@Composable
fun HomeScreen(userName: String, userEmail: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Chào mừng, $userName!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "Email: $userEmail", fontSize = 18.sp)
    }
}
