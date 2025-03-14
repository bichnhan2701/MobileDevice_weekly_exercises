package com.example.jetpackcomposeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

val componentCategories = mapOf(
    "Display" to listOf("Text", "Image", "Icon"),
    "Input" to listOf("TextField", "PasswordField", "Interactive"),
    "Layout" to listOf("Column", "Row", "Box")
)

@Composable
fun ComponentListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Text(
            text = "UI Components List",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Blue
        )

        Spacer(modifier = Modifier.height(16.dp))

        componentCategories.forEach{ (category, components) ->
            Text(
                text = category,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            components.forEach{ component ->
                ComponentCard(navController, component)
            }
        }
    }
}

@Composable
fun ComponentCard(navController: NavController, component: String) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { navController.navigate("component_detail/$component") },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB))
    ) {
        Column (modifier = Modifier.padding(16.dp)) {
            Text(text = component, style = MaterialTheme.typography.headlineSmall)
            Text(text = "Description of $component", color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComponentListScreenPreview() {
    JetpackComposeAppTheme {
        ComponentListScreen(navController = rememberNavController())
    }
}