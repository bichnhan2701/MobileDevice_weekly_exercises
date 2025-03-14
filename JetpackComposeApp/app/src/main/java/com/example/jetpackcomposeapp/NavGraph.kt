package com.example.jetpackcomposeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackcomposeapp.ui.screens.ComponentDetailScreen
import com.example.jetpackcomposeapp.ui.screens.ComponentListScreen
import com.example.jetpackcomposeapp.ui.screens.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {
        composable("main_screen") { MainScreen(navController)}
        composable("component_list") {ComponentListScreen(navController)}
        composable("component_detail/{componentName}") { backStackEntry ->
            val componentName = backStackEntry.arguments?.getString("componentName") ?: "Unkown"
            ComponentDetailScreen(componentName)
        }
    }
}