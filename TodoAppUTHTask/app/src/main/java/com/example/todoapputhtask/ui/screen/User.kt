package com.example.todoapputhtask.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.todoapputhtask.R
import com.example.todoapputhtask.ui.viewmodel.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun User(navController: NavController,  viewModel: AuthViewModel = viewModel()) {
    val user = FirebaseAuth.getInstance().currentUser  // Lấy thông tin người dùng từ Firebase

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Profile",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3991D8)
            )
        )

        Box (
            modifier = Modifier
                .size(170.dp)
                .padding(8.dp)
                .padding(start = 10.dp)
        ) {
            Image(
                painter = if (user?.photoUrl != null) rememberAsyncImagePainter(user.photoUrl)
                else painterResource(R.drawable.uthlogonobg),
                contentDescription = "User image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape)
            )
            Image(
                painter = painterResource(R.drawable.vector),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.BottomEnd)
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ){
            Text(
                text = "Email",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            OutlinedTextField(
                value = user?.email ?: "Chưa cập nhật",
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ){
            Text(
                text = "Name",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            OutlinedTextField(
                value = user?.displayName ?: "Chưa cập nhật",
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ){
            Text(
                text = "Date of birth",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            OutlinedTextField(
                value = "Chưa cập nhật",
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(onClick = {
            viewModel.logout()
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Đăng xuất", fontSize = 16.sp, color = Color.White, textAlign = TextAlign.Center)
        }

    }
}