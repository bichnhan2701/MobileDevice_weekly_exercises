package com.example.uthnavigationlazycolumn.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uthnavigationlazycolumn.ui.theme.UTHNavigationLazycolumnTheme

@Composable
fun DetailScreen (navController: NavController, index: Int){
    Column (
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth() .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigate("list")},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(90.dp))
            Text(
                text = "Detail",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
        Text(
            text = "\"The only way to do great work is to love what you do.\"",
            fontSize = 18.sp,
            modifier = Modifier.padding(20.dp),
            style = TextStyle(textAlign = TextAlign.Center)
        )
        DetailItem()
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = { navController.navigate("root")},
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "BACK TO ROOT",
                fontSize = 20.sp,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
fun DetailItem() {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.White, Color(0xFFA1E3F9))
    )
    Column  (
        modifier = Modifier
            .padding(16.dp)
            .background(gradientBrush)
            .width(320.dp) .height(450.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "\"The only way to do great work is to love what you do.\"",
            fontSize = 55.sp,
            overflow = TextOverflow.Visible,
            style = TextStyle(
                lineHeight = 60.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(10.dp)
        )
        Text(
            "Steve Jobs",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "http://quotes.thisgrandpablogs.com/",
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Preview (showBackground = true)
@Composable
fun DetailScreenPreview() {
    UTHNavigationLazycolumnTheme {
        DetailScreen(navController = rememberNavController(), 1)
    }
}