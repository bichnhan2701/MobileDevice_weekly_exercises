package com.example.todoapputhtask.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapputhtask.data.model.toTask
import com.example.todoapputhtask.data.repository.TaskRepository
import com.example.todoapputhtask.ui.component.EmptyView
import com.example.todoapputhtask.ui.component.Header
import com.example.todoapputhtask.ui.component.TaskItem
import com.example.todoapputhtask.viewmodel.TaskListViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home(navController: NavController, repository: TaskRepository) {
    Header(navController)

//    val context = LocalContext.current
//    val db = TaskDatabase.getInstance(context)
    //val repository = TaskRepository(db.taskDao())
    //val scope = rememberCoroutineScope()
    val viewModel = remember { TaskListViewModel(repository) }

    val taskList by viewModel.tasks.collectAsState()
    val user = FirebaseAuth.getInstance().currentUser

    LaunchedEffect(Unit) {
        viewModel.loadTasksFromApi()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (taskList.isEmpty()) {
            EmptyView()
        } else {
            Column {
                if (user != null) {
                    Text(
                        text = "Xin chào, ${user.displayName ?: "Người dùng"}!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF3991D8),
                        modifier = Modifier.padding(top = 70.dp, start = 20.dp)
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    items(taskList) { task ->
                        TaskItem(task = task.toTask(), onClick = {
                            navController.navigate("detail/${task.id}")
                        })
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                navController.navigate("add")
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 24.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Thêm Task")
        }
    }
}
