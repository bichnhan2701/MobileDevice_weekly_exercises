package com.example.theme.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theme.datastore.DataStoreManager
import kotlinx.coroutines.launch

@Composable
fun ThemeSelectorApp(dataStoreManager: DataStoreManager) {
    val themeColors = listOf(
        Color(0xFFBBDEFB), // Light Blue
        Color(0xFFE040FB), // Pink
        Color(0xFF212121)  // Dark
    )
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var selectedColor by remember { mutableStateOf<Color?>(null) }
    var appliedColor by remember { mutableStateOf(Color.White) }

    // Read saved theme from DataStore
    LaunchedEffect(Unit) {
        dataStoreManager.themeColorFlow.collect { hex ->
            try {
                hex?.let {
                    appliedColor = Color(android.graphics.Color.parseColor(it))
                }
            } catch (e: IllegalArgumentException) {
                // log lỗi và dùng màu mặc định
                appliedColor = Color.White
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appliedColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Setting",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = if (appliedColor == Color(0xFF212121)) Color.White else Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Choosing the right theme sets the tone and personality of your app",
                color = if (appliedColor == Color(0xFF212121)) Color.LightGray else Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                themeColors.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(60.dp, 40.dp)
                            .background(color, shape = RoundedCornerShape(8.dp))
                            .clickable { selectedColor = color }
                            .border(
                                width = if (selectedColor == color) 3.dp else 0.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    selectedColor?.let {
                        appliedColor = it
//                        val hex = "#${it.value.toULong().toString(16).padStart(8, '0').substring(2)}"
                        val hex = "#%06X".format(0xFFFFFF and it.toArgb())
                        scope.launch {
                            dataStoreManager.saveThemeColor(hex)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(50.dp)
                    .width(140.dp)
            ) {
                Text("Apply", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
