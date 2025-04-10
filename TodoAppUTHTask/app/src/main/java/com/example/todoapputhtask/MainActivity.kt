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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todoapputhtask.data.local.TaskDatabase
import com.example.todoapputhtask.data.repository.TaskRepository
import com.example.todoapputhtask.ui.component.BottomNavComponent
import com.example.todoapputhtask.ui.navigation.NavGraph
import com.example.todoapputhtask.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = TaskDatabase.getInstance(applicationContext)
        val repository = TaskRepository(db.taskDao())

        setContent {
            val navController = rememberNavController()
            val isLoggedIn by viewModel.isLoggedIn.observeAsState(initial = false)
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

            Scaffold(
                bottomBar = {
                    if (currentRoute != "login") {
                        BottomNavComponent(navController)
                    }
                },
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
            ) { paddingValues ->
                NavGraph(
                    navController = navController,
                    isLoggedIn = isLoggedIn,
                    modifier = Modifier.padding(paddingValues),
                    repository = repository
                )
            }
        }
    }
}
