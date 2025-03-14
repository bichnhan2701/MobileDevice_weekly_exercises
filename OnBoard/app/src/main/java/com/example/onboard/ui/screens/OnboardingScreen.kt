package com.example.onboard.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onboard.R

@Composable
fun OnboardingScreen (navController: NavController){
    val pages = listOf(
        OnboardingPage(
            "Easy Time Management",
            "With management based on priority and daily tasks, " +
                    "it will give you convenience in managing " +
                    "and determining the tasks that must be done first ",
            R.drawable.bro1
        ),
        OnboardingPage(
            "Increase Work Effectiveness",
            "Time management and the determination of more important tasks" +
                    " will give your job statistics better and always improve",
            R.drawable.bro2
        ),
        OnboardingPage(
            "Reminder Notification",
            "The advantage of this application is that " +
                    "it also provides reminders for you so you don't forget " +
                    "to keep doing your assignments well and according to the time you have set",
            R.drawable.bro3
        )
    )

    var pageIndex by remember { mutableStateOf(0)}

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .systemBarsPadding()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DotsIndicator(pages.size, pageIndex)

            ClickableText (
                text = AnnotatedString("Skip"),
                style = LocalTextStyle.current.copy(
                    fontSize = 14.sp,
                    color = Color.Blue
                ),
                modifier = Modifier.padding(end = 16.dp),
                onClick = {
                    navController.navigate("home")
                }
            )
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = pages[pageIndex].image),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )

            Text(
                text = pages[pageIndex].title,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier. height(12.dp))

            Text (
                text = pages[pageIndex].description,
                fontSize = 17.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            if (pageIndex > 0) {
                Button(
                    onClick = {pageIndex --},
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Text(text = "Back", color = Color.White, fontSize = 16.sp)
                }
            } else {
                Spacer(modifier = Modifier.width(100.dp))
            }

            Button(
                onClick = {
                    if (pageIndex < pages.size - 1) {
                        pageIndex ++
                    } else {
                        navController.navigate("home")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text(text = "Next", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun DotsIndicator(size: Int, currentIndex : Int ) {
    Row (
        modifier = Modifier.padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(size) {
            Box (
                modifier = Modifier
                    .size(if (it == currentIndex) 10.dp else 8.dp)
                    .background(
                        if (it == currentIndex) Color.Blue
                        else Color.Gray.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .padding(4.dp)
            )
        }
    }
}

data class OnboardingPage(
    val title: String,
    val description: String,
    val image: Int
)