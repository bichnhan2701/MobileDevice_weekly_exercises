package com.example.todoapputhtask.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapputhtask.R
import com.example.todoapputhtask.data.local.TaskDatabase
import com.example.todoapputhtask.data.model.toTask
import com.example.todoapputhtask.data.repository.TaskRepository
import com.example.todoapputhtask.ui.theme.CardColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Detail (taskId: Int, navController: NavController, repository: TaskRepository){
    HeadDetail(navController = navController, taskId = taskId, repository = repository)

//    val context = LocalContext.current
//    val db = TaskDatabase.getInstance(context)
    val taskList by repository.getLocalTasks().collectAsState(initial = emptyList())

    val task = taskList.find { it.id == taskId }

    task?.toTask()?.let {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 60.dp)
                .fillMaxSize(),
        ) {
            Text(
                text = it.title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = it.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .background(CardColors[taskId % CardColors.size])
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row (
                    modifier = Modifier
                        .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.category),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp) .padding(5.dp)
                    )
                    Column (
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Category",
                            fontSize = 12.sp
                        )
                        Text(
                            text = it.category,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Row (
                    modifier = Modifier
                    .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.status),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp) .padding(5.dp)
                    )
                    Column (
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Status",
                            fontSize = 12.sp
                        )
                        Text(
                            text = it.status,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Row (
                    modifier = Modifier
                        .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.priority),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp) .padding(5.dp)
                    )
                    Column (
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Priority",
                            fontSize = 12.sp
                        )
                        Text(
                            text = it.priority,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Subtasks:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
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
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = subtask.isCompleted,
                                onCheckedChange = null
                            )
                            Text(subtask.title, fontSize = 16.sp)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Attachments:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
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
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.attach),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp) .padding(5.dp)
                            )
                            Text(attachment.fileName, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    } ?: CircularProgressIndicator()
}

@Composable
fun HeadDetail(navController: NavController, taskId: Int, repository: TaskRepository) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    // ✅ Dialog xác nhận xoá
    if (showDialog) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Text(
                    "Xoá",
                    color = Color.Red,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            showDialog = false
                            // Gọi coroutine để xoá
                            CoroutineScope(Dispatchers.IO).launch {
                                val db = TaskDatabase.getInstance(context)
                                val task = db.taskDao().getTaskById(taskId)
                                task?.let {
                                    repository.deleteLocalTask(it)
                                }
                                withContext(Dispatchers.Main) {
                                    navController.navigate("home") {
                                        popUpTo("detail/$taskId") { inclusive = true }
                                    }
                                }
                            }
                        }
                )
            },
            dismissButton = {
                Text(
                    "Huỷ",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { showDialog = false }
                )
            },
            title = { Text("Xác nhận xoá") },
            text = { Text("Bạn có chắc muốn xoá task này không?") }
        )
    }

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
            text = "Detail",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3991D8)
            )
        )
//        Icon(
//            modifier = Modifier.size(36.dp),
//            imageVector = Icons.Default.Delete,
//            contentDescription = "Delete Icon",
//            tint = Color.Red
//        )
        Icon(
            modifier = Modifier
                .size(36.dp)
                .clickable {
                    showDialog = true
                },
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Icon",
            tint = Color.Red
        )
    }
}
