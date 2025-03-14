package com.example.jetpackcomposeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.ui.components.BoxLayout
import com.example.jetpackcomposeapp.ui.components.ColumnLayout
import com.example.jetpackcomposeapp.ui.components.IconComponent
import com.example.jetpackcomposeapp.ui.components.ImageComponent
import com.example.jetpackcomposeapp.ui.components.OtherField
import com.example.jetpackcomposeapp.ui.components.PasswordField
import com.example.jetpackcomposeapp.ui.components.RowLayout
import com.example.jetpackcomposeapp.ui.components.TextComponent
import com.example.jetpackcomposeapp.ui.components.TextFieldComponent

@Composable
fun ComponentDetailScreen(componentName: String) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Details of $componentName", style = MaterialTheme.typography.headlineMedium, color = Color.Blue)
        Spacer(modifier = Modifier.height(16.dp))

        when (componentName) {
            "Text" -> TextComponent()
            "Image" -> ImageComponent()
            "Icon" -> IconComponent()
            "TextField" -> TextFieldComponent()
            "PasswordField" -> PasswordField()
            "Interactive" -> OtherField()
            "Column" -> ColumnLayout()
            "Row" -> RowLayout()
            "Box" -> BoxLayout()
            else -> Text(text = "No component available")
        }
    }
}
