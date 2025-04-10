package com.example.todoapputhtask.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapputhtask.R
import com.example.todoapputhtask.viewmodel.AddTaskViewModel

@Composable
fun Add (navController: NavController, viewModel: AddTaskViewModel, onSuccess: () -> Unit) {
    HeadAdd(navController)

    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val isAdded by viewModel.isAdded.collectAsState()
    //val error by viewModel.error.collectAsState()

    if (isAdded) {
        LaunchedEffect(Unit) {
            viewModel.resetState()
            onSuccess()
        }
    }

    Column (
        modifier = Modifier.fillMaxWidth() .padding(16.dp) .padding( top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            Text(
                text = "Task",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = title,
                onValueChange = {viewModel.onTitleChange(it)},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Description",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { viewModel.onDescriptionChange(it) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { viewModel.addTaskToLocal() },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Thêm Task")
            }
        }
    }
}

@Composable
fun HeadAdd(navController: NavController) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = "Back icon",
            modifier = Modifier.size(36.dp) .padding(5.dp)
                .clickable {
                    navController.navigate("home")
                }
        )
        Text(
            text = "Add New Task",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3991D8)
            )
        )
        Icon(
            modifier = Modifier.size(36.dp),
            imageVector = Icons.Default.Done,
            contentDescription = "Save Icon",
            tint = Color.Red
        )
    }
}