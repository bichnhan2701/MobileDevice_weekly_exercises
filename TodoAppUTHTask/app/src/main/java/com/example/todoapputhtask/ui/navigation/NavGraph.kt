package com.example.todoapputhtask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapputhtask.ui.screen.Add
import com.example.todoapputhtask.ui.screen.Calender
import com.example.todoapputhtask.ui.screen.Detail
import com.example.todoapputhtask.ui.screen.Home
import com.example.todoapputhtask.ui.screen.List
import com.example.todoapputhtask.ui.screen.Login
import com.example.todoapputhtask.ui.screen.User

@Composable
fun NavGraph(navController: NavHostController, isLoggedIn: Boolean, modifier: Modifier = Modifier) {
    NavHost(
        navController,
        startDestination = if (isLoggedIn) "home" else "login",
        modifier = modifier
    ) {
        composable("login") { Login(navController) }
        composable("home") { Home(navController) }

        composable(BottomNavigationBar.Home.route) { Home(navController) }
        composable(BottomNavigationBar.Add.route) { Add(navController) }
        composable(BottomNavigationBar.List.route) { List(navController) }
        composable(BottomNavigationBar.Calender.route) { Calender(navController) }
        composable(BottomNavigationBar.User.route) { User(navController) }
        composable("detail/{taskId}") { backStackEntry ->
            Detail(backStackEntry.arguments?.getString("taskId")?.toInt() ?: 0, navController)
        }

    }
}

