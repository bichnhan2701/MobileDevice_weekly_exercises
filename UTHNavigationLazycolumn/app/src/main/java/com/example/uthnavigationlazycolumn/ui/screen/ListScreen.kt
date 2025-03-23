package com.example.uthnavigationlazycolumn.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uthnavigationlazycolumn.ui.theme.UTHNavigationLazycolumnTheme

@Composable
fun ListScreen (navController: NavController){
    Column (
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(top = 30.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth() .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigate("root")},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(90.dp))
            Text(
                text = "List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }

        LazyColumn  {
            items(1000000) { index ->
                ListItem(index, navController)
            }
        }
    }
}

@Composable
fun ListItem(index: Int, navController: NavController) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("detail/${index}") }
            .padding(16.dp)
            .background(color = Color(0xFFA1E3F9)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "${index + 1} | The only way to do great work is to love what you do.",
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp) .width(300.dp),
            overflow = TextOverflow.Visible
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            modifier = Modifier.padding(10.dp),
        )
    }
}

@Preview( showBackground = true )
@Composable
fun ListScreenPreview() {
    UTHNavigationLazycolumnTheme {
        ListScreen(navController = rememberNavController())
    }
}