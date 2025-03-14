package com.example.jetpackcomposeapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun IconComponent() {
    var clickCount by remember { mutableIntStateOf(0) }

    var color = when (clickCount % 2) {
        1 -> Color.Red
        else -> Color.Black
    }

    Column{
        Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite", tint = Color.Red)
        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
        Icon(imageVector = Icons.Default.Person, contentDescription = "Person")
        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "ShoppingCart")
        Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
        Icon(imageVector = Icons.Default.Star, contentDescription = "Star", tint = Color.Yellow)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Clickable icon")
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "FavoriteBorder",
            tint = color,
            modifier = Modifier
                .clickable { clickCount ++ }
        )
    }
}

@Preview( showBackground = true)
@Composable
fun IconComponentPreview() {
    JetpackComposeAppTheme {
        IconComponent()
    }
}