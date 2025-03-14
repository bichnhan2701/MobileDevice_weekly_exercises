package com.example.jetpackcomposeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeapp.R
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun BoxLayout () {
    Column {
        Box(
            modifier =  Modifier
                .size(300.dp)
                .background(Color.LightGray)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Box Layout",
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Cyan)
            ) {
                Text(
                    text = "Box Layout",
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Magenta)
            ) {
                Text(
                    text = "Box Layout",
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.meo_loading),
                contentScale = ContentScale.Crop,
                contentDescription = "Meo Loading Background",
                modifier = Modifier.fillMaxSize()
            )

            Text(
                text = "Hi, I am Meo.",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .background(Color.LightGray.copy(alpha = 0.5f))
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxLayoutPreview() {
    JetpackComposeAppTheme {
        BoxLayout()
    }
}