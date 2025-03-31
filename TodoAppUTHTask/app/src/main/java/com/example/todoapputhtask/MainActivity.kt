package com.example.todoapputhtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todoapputhtask.ui.component.BottomNavComponent
import com.example.todoapputhtask.ui.model.AuthViewModel
import com.example.todoapputhtask.ui.navigation.NavGraph

class MainActivity : ComponentActivity() {
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val isLoggedIn by viewModel.isLoggedIn.observeAsState(initial = false)
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

            // ✅ Điều hướng ngay khi trạng thái đăng nhập thay đổi
            LaunchedEffect(isLoggedIn) {
                if (isLoggedIn) {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true } // Xóa login khỏi backstack
                    }
                } else {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true } // Xóa home khỏi backstack
                    }
                }
            }

            Scaffold(
                bottomBar = {
                    if (currentRoute != "login") { // ✅ Ẩn Navbar nếu ở màn hình Login
                        BottomNavComponent(navController)
                    }
                },
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
            ) { paddingValues ->
                NavGraph(navController, isLoggedIn, Modifier.padding(paddingValues))
            }
        }
    }
}
