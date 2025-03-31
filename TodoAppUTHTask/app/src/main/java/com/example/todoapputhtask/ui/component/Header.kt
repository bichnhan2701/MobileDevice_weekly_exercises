package com.example.todoapputhtask.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todoapputhtask.R
import com.example.todoapputhtask.ui.theme.TodoAppUTHTaskTheme

@Composable
fun Header(navController: NavController) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(5.dp)
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.uthlogo),
            contentDescription = "UTH logo",
            modifier = Modifier.size(60.dp) .clickable {
                navController.navigate("home")
            },
        )
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "SmartTasks",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3991D8)
                )
            )
            Text(
                text = "A simple and efficient to do app",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF3991D8)
                )
            )
        }
        Image(
            painter = painterResource(R.drawable.notification),
            contentDescription = "Notification Icon",
            modifier = Modifier.size(36.dp) .padding(5.dp)
        )
    }
}

@Preview( showBackground = true )
@Composable
fun HeaderPreview() {
    TodoAppUTHTaskTheme {
        Header(navController = rememberNavController())
    }
}