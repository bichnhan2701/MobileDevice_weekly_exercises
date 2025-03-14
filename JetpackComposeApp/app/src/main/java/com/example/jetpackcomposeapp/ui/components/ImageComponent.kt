package com.example.jetpackcomposeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.R
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

val triangleShape = GenericShape{ size, _ ->
    moveTo(size.width / 2, 0f)
    lineTo(size.width, size.height)
    lineTo(0f, size.height)
    close()
}

val hexagonShape = GenericShape {size, _ ->
    val width = size.width
    val height = size.height
    moveTo(width * 0.25f, 0f)
    lineTo(width * 0.75f, 0f)
    lineTo(width, height * 0.5f)
    lineTo(width * 0.75f, height)
    lineTo(width * 0.25f, height)
    lineTo(0f, height * 0.5f)
    close()
}

@Composable
fun ImageComponent() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.meo_loading),
            contentDescription = "Square Meo Loading Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.meo_loading),
            contentDescription = "Circle Meo Loading Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.meo_loading),
            contentDescription = "Rounded Corner Meo Loading Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.meo_loading),
            contentDescription = "Different Rounded Corner Meo Loading Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, bottomEnd = 32.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.meo_loading),
            contentDescription = "Oval Meo Loading Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp, 50.dp)
                .clip(RoundedCornerShape(50.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.meo_loading),
            contentDescription = "Triangle Meo Loading Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(triangleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.meo_loading),
            contentDescription = "Hexagon Meo Loading Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(hexagonShape)
        )
    }
}

@Preview (showBackground = true)
@Composable
fun ImageComponentPreview() {
    JetpackComposeAppTheme { 
        ImageComponent()
    }
}