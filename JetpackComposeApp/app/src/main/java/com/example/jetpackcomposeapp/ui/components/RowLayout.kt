package com.example.jetpackcomposeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun RowLayout() {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Item 1")
            Text(text = "Item 2")
            Text(text = "Item 3")
            Text(text = "Item 4")
            Text(text = "Item 5")
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(R.drawable.meo_loading),
                contentDescription = "Meo Loading Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )

            Image(
                painter = painterResource(R.drawable.meo_loading),
                contentDescription = "Meo Loading Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )

            Image(
                painter = painterResource(R.drawable.meo_loading),
                contentDescription = "Meo Loading Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )

            Image(
                painter = painterResource(R.drawable.meo_loading),
                contentDescription = "Meo Loading Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )

            Image(
                painter = painterResource(R.drawable.meo_loading),
                contentDescription = "Meo Loading Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )
        }
    }

}

@Preview (showBackground = true)
@Composable
fun RowLayoutPreview() {
    JetpackComposeAppTheme {
        RowLayout()
    }
}