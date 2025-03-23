package com.example.todoapputhtask.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationBar (
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home: BottomNavigationBar("home", "Home", Icons.Default.Home)
    object Calender: BottomNavigationBar("calender", "Calender", Icons.Default.DateRange)
    object Add: BottomNavigationBar("add", "Add Task", Icons.Default.AddCircle)
    object List: BottomNavigationBar("list", "List", Icons.AutoMirrored.Filled.List)
    object Setting: BottomNavigationBar("setting", "Setting", Icons.Default.Settings)
}