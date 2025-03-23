package com.example.todoapputhtask.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.todoapputhtask.ui.navigation.BottomNavigationBar

@Composable
fun BottomNavComponent (navController: NavController) {
    val items = listOf(
        BottomNavigationBar.Home,
        BottomNavigationBar.Calender,
        BottomNavigationBar.Add,
        BottomNavigationBar.List,
        BottomNavigationBar.Setting
    )

    NavigationBar (
        containerColor = Color.LightGray,
        modifier = Modifier.height(70.dp)
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon( screen.icon, screen.title)},
                label = { Text(screen.title)},
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
//                        popUpTo(navController.graph.startDestinationId) { saveState = true}
//                        launchSingleTop = true
//                        restoreState = true
                    }
                }
            )
        }
    }
}