package com.example.todoapputhtask.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapputhtask.ui.component.BottomNavComponent
import com.example.todoapputhtask.ui.screen.Detail
import com.example.todoapputhtask.ui.screen.Home

@Composable
fun NavGraph (navController: NavHostController = rememberNavController()){
    Scaffold (
        bottomBar = { BottomNavComponent(navController)},
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavigationBar.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable( BottomNavigationBar.Home.route) { Home(navController) }
            composable("detail/{taskId}") {backStackEntry ->
                Detail(backStackEntry.arguments?.getString("taskId")?.toInt() ?: 0, navController)
            }
        }
    }
}