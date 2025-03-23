package com.example.uthtaskresrfulapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthtaskresrfulapi.ui.screen.TaskDetailScreen
import com.example.uthtaskresrfulapi.ui.screen.TaskListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "list") {
                composable("list") { TaskListScreen(navController) }
                composable("detail/{taskId}") {backStackEntry ->
                    TaskDetailScreen(backStackEntry.arguments?.getString("taskId")?.toInt() ?: 0)
                }
            }
        }
    }
}
