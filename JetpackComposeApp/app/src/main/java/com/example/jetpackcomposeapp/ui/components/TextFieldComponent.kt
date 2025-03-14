package com.example.jetpackcomposeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun TextFieldComponent() {

    var text by remember { mutableStateOf("") }

    Column {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = "Text field: ")
            Spacer(modifier = Modifier.width(65.dp))
            TextField(
                value = text,
                onValueChange = {text = it},
                label = {Text("Your name")}
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .padding(8.dp)
                .width(300.dp)
        ) {
            Text(text = "Outline Text field: ")
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                value = text,
                onValueChange = {text = it},
                label = {Text("Your name")}
            )
        }
    }
}

@Preview( showBackground = true )
@Composable
fun TextFieldComponentPreView() {
    JetpackComposeAppTheme {
        TextFieldComponent()
    }
}