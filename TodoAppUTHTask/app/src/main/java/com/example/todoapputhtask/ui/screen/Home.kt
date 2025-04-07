package com.example.todoapputhtask.ui.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapputhtask.data.model.Task
import com.example.todoapputhtask.data.repository.TaskRepository
import com.example.todoapputhtask.ui.component.EmptyView
import com.example.todoapputhtask.ui.component.Header
import com.example.todoapputhtask.ui.component.TaskItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun Home (navController: NavController){
    Header(navController)

    val coroutineScope = rememberCoroutineScope()
    var taskList by remember { mutableStateOf<List<Task>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    val user = FirebaseAuth.getInstance().currentUser

    LaunchedEffect (Unit) {
        coroutineScope.launch {
            val tasks = TaskRepository.getTasks()
            taskList = tasks.sortedByDescending { it.id } // hoặc it.id nếu có
            isLoading = false
        }
    }
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize()
        )
    } else if ( taskList.isEmpty() ) {
        EmptyView()
    } else {
        if (user != null) {
            Text(
                text = "Xin chào, ${user.displayName ?: "Người dùng"}!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF3991D8),
                modifier = Modifier.padding(top = 70.dp) .padding(start = 20.dp)
            )
        }

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 80.dp)
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            items(taskList) { task ->
                TaskItem(task, onClick = {
                    navController.navigate("detail/${task.id}")
                })
            }
        }

        FloatingActionButton(onClick = {
            navController.navigate("add")
        }) {
            Icon(Icons.Default.Add, contentDescription = "Thêm Task")
        }
    }
}

