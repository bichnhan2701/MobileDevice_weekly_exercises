package com.example.todoapputhtask.ui.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapputhtask.data.model.Task
import com.example.todoapputhtask.data.repository.TaskRepository
import com.example.todoapputhtask.ui.component.EmptyView
import com.example.todoapputhtask.ui.component.Header
import com.example.todoapputhtask.ui.component.TaskItem
import kotlinx.coroutines.launch

@Composable
fun Home (navController: NavController){
    Header(navController)

    val coroutineScope = rememberCoroutineScope()
    var taskList by remember { mutableStateOf<List<Task>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect (Unit) {
        coroutineScope.launch {
            taskList = TaskRepository.getTasks()
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
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 60.dp)
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            items(taskList) { task ->
                TaskItem(task, onClick = {
                    navController.navigate("detail/${task.id}")
                })
            }
        }
    }
}

