package com.example.jetpackcomposeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import com.example.jetpackcomposeapp.ui.theme.PlaywriteHUFontFamily

@Composable
fun TextComponent() {
    Column {
        Text(text = "Nomarl text")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Bold text" , fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Italic text", fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Underline text", style = TextStyle(textDecoration = TextDecoration.Underline))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Strikethrough text", style = TextStyle(textDecoration = TextDecoration.LineThrough))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Text", fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Large text fontsize 24", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text (text = "Text font", style = TextStyle(fontFamily = PlaywriteHUFontFamily))
    }
}

@Preview(showBackground = true)
@Composable
fun TextComponentPreview() {
    JetpackComposeAppTheme {
        TextComponent()
    }
}