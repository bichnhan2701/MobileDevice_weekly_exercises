package com.example.uthnavigationlazycolumn

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uthnavigationlazycolumn.ui.screen.DetailScreen
import com.example.uthnavigationlazycolumn.ui.screen.ListScreen
import com.example.uthnavigationlazycolumn.ui.screen.RootScreen

@Composable
fun NavGraph (navController: NavHostController) {
    NavHost(navController, startDestination = "root") {
        composable("root") { RootScreen(navController) }
        composable("list") { ListScreen(navController) }
        composable("detail/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index") ?: "0"
            DetailScreen(navController, index.toInt())
        }
    }
}