package com.example.uthtaskresrfulapi.ui.screen

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uthtaskresrfulapi.data.model.Task
import com.example.uthtaskresrfulapi.data.repository.TaskRepository
import com.example.uthtaskresrfulapi.ui.theme.UTHTaskRESRfulAPITheme
import kotlinx.coroutines.launch

@Composable
fun TaskDetailScreen(taskId: Int) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(top = 30.dp),
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
    var task by remember { mutableStateOf<Task?>(null) }

    LaunchedEffect(taskId) {
        coroutineScope.launch {
            val tasks = TaskRepository.getTasks()
            task = tasks.find { it.id == taskId }
        }
    }

    task?.let {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 60.dp)
                .fillMaxSize()
        ) {
            Text(
                text = it.title,
                style = TextStyle(
                    fontSize = 24.sp,              // Cỡ chữ
                    fontWeight = FontWeight.Bold,  // Độ đậm
                ),
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = it.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Subtasks:",
                style = TextStyle(
                    fontSize = 24.sp,              // Cỡ chữ
                    fontWeight = FontWeight.Bold,  // Độ đậm
                ),
            )
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(it.subtasks) { subtask ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                        elevation = CardDefaults.cardElevation(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Text("- ${subtask.title} (${if (subtask.isCompleted) "✔" else "❌"})")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Attachments:",
                style = TextStyle(
                    fontSize = 24.sp,              // Cỡ chữ
                    fontWeight = FontWeight.Bold,  // Độ đậm
                ),
            )
            LazyColumn {
                items(it.attachments) { attachment ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                        elevation = CardDefaults.cardElevation(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Text("- ${attachment.fileName}")
                        }
                    }
                }
            }
        }
    } ?: CircularProgressIndicator()
}

@Preview( showBackground = true)
@Composable
fun TaskDetailScreenPreview() {
    UTHTaskRESRfulAPITheme {
        TaskDetailScreen(1)
    }
}