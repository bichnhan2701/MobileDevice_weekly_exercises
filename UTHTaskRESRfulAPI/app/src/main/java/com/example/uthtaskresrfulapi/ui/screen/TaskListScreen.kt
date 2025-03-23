package com.example.uthtaskresrfulapi.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uthtaskresrfulapi.R
import com.example.uthtaskresrfulapi.data.model.Task
import com.example.uthtaskresrfulapi.data.repository.TaskRepository
import com.example.uthtaskresrfulapi.ui.theme.CardColors
import com.example.uthtaskresrfulapi.ui.theme.UTHTaskRESRfulAPITheme
import kotlinx.coroutines.launch


@Composable
fun TaskListScreen(navController: NavController) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "Back icon"
        )
        Text(
            text = "List Task",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        )
        Icon(
            modifier = Modifier.size(36.dp),
            imageVector = Icons.Default.Add,
            contentDescription = "Add icon"
        )
    }


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

@Composable
fun EmptyView() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.nottaskyet),
            contentDescription = "Not Task Yet Image",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(contentAlignment = Alignment.Center) {
            Text(text = "No Tasks Yet!\nStay productive—add something to do", textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() },
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColors[task.id % CardColors.size]
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp) .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = task.title,
                style = TextStyle(
                    fontSize = 20.sp,              // Cỡ chữ
                    fontWeight = FontWeight.Bold,  // Độ đậm
                    lineHeight = 30.sp,            // Chiều cao dòng
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview( showBackground = true)
@Composable
fun TaskListScreenPreview() {
    UTHTaskRESRfulAPITheme {
        TaskListScreen(navController = rememberNavController())
    }
}