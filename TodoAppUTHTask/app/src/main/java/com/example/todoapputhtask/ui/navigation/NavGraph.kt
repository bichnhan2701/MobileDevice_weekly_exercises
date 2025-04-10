package com.example.todoapputhtask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapputhtask.data.repository.TaskRepository
import com.example.todoapputhtask.ui.screen.Add
import com.example.todoapputhtask.ui.screen.Calender
import com.example.todoapputhtask.ui.screen.Detail
import com.example.todoapputhtask.ui.screen.Home
import com.example.todoapputhtask.ui.screen.List
import com.example.todoapputhtask.ui.screen.Login
import com.example.todoapputhtask.ui.screen.User
import com.example.todoapputhtask.viewmodel.AddTaskViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    isLoggedIn: Boolean,
    modifier: Modifier = Modifier,
    repository: TaskRepository // 👈 Thêm vào đây
) {
    NavHost(
        navController,
        startDestination = if (isLoggedIn) "home" else "login",
        modifier = modifier
    ) {
        composable("login") { Login(navController) }

        composable("home") {
            Home(navController = navController, repository = repository)
        }

        composable(BottomNavigationBar.Home.route) {
            Home(navController = navController, repository = repository)
        }

        composable("detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
            Detail(taskId = taskId, navController = navController, repository = repository)
        }

        composable(BottomNavigationBar.Add.route) {
            Add(
                navController = navController,
                viewModel = AddTaskViewModel(repository),
                onSuccess = {
                    navController.popBackStack()
                }
            )
        }

        composable("add") {
            Add(
                navController = navController,
                viewModel = AddTaskViewModel(repository),
                onSuccess = {
                    navController.popBackStack()
                }
            )
        }

        // Các màn khác (giữ nguyên)
        composable(BottomNavigationBar.List.route) { List(navController) }
        composable(BottomNavigationBar.Calender.route) { Calender(navController) }
        composable(BottomNavigationBar.User.route) { User(navController) }
    }
}

