package com.example.jetpackcomposeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.R
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun ColumnLayout() {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Item 1")
        Image(
            painter = painterResource(R.drawable.meo_loading),
            contentDescription = "Meo Loading Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(150.dp)
        )
        Text(text = "Item 2")
        Image(
            painter = painterResource(R.drawable.meo_loading),
            contentDescription = "Meo Loading Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(150.dp)
        )
        Text(text = "Item 3")
        Image(
            painter = painterResource(R.drawable.meo_loading),
            contentDescription = "Meo Loading Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(150.dp)
        )
    }
}

@Preview (showBackground = true)
@Composable
fun ColumnLayoutPreview() {
    JetpackComposeAppTheme {
        ColumnLayout()
    }
}