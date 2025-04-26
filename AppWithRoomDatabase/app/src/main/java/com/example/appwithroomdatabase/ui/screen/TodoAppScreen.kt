package com.example.appwithroomdatabase.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appwithroomdatabase.data.Todo
import com.example.appwithroomdatabase.ui.viewmodel.TodoViewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    val todos by viewModel.allTodos.collectAsState()
    var text by remember { mutableStateOf("") }
    var editingTodo by remember { mutableStateOf<Todo?>(null) }

    var showDialog by remember { mutableStateOf(false) }
    var todoToDelete by remember { mutableStateOf<Todo?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    Column(Modifier.padding(16.dp)) {
        Text(
            text = "Công việc hàng ngày",
            style = TextStyle(
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF003092)
            ),
            modifier = Modifier.padding(16.dp) .align(Alignment.CenterHorizontally)
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier.weight(1f)
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        errorMessage = null
                    },
                    label = { Text("Nhập công việc") },
                    isError = errorMessage != null,
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorMessage != null) {
                    Text(
                        text = errorMessage ?: "",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (text.isBlank()) {
                        errorMessage = "Vui lòng nhập công việc"
                        return@Button
                    }

                    if (editingTodo == null) {
                        viewModel.insert(Todo(title = text))
                    } else {
                        viewModel.update(editingTodo!!.copy(title = text))
                        editingTodo = null
                    }
                    text = ""
                    errorMessage = null
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDAEAF7),
                    contentColor = Color(0xFF003092)
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .height(56.dp)
            ) {
                Text(if (editingTodo == null) "Thêm" else "Cập nhật")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Danh sách công việc",
            style = TextStyle(
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF003092)
            ),
            modifier = Modifier .align(Alignment.Start)
        )

        LazyColumn {
            items(todos) { todo ->
                TodoItem(
                    todo = todo,
                    onEdit = {
                        editingTodo = it
                        text = it.title
                    },
                    onDelete = {
                        todoToDelete = it
                        showDialog = true
                    }
                )
            }
        }

        DeleteConfirmationDialog(
            showDialog = showDialog,
            onConfirmDelete = {
                todoToDelete?.let { viewModel.delete(it) }
                showDialog = false
                todoToDelete = null
            },
            onDismiss = {
                showDialog = false
                todoToDelete = null
            }
        )

    }
}

@Composable
fun TodoItem(todo: Todo, onEdit: (Todo) -> Unit, onDelete: (Todo) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFDAEAF7)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = todo.title,
                modifier = Modifier.weight(1f)
            )
            Row {
                IconButton(onClick = { onEdit(todo) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = { onDelete(todo) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error // đỏ
                    )
                }
            }
        }
    }
}

@Composable
fun DeleteConfirmationDialog(
    showDialog: Boolean,
    onConfirmDelete: () -> Unit,
    onDismiss: () -> Unit,
    title: String = "Xác nhận xoá",
    message: String = "Bạn có chắc chắn muốn xoá mục này?",
    confirmText: String = "Xoá",
    dismissText: String = "Hủy"
) {
    if (!showDialog) return

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirmDelete) {
                Text(confirmText, color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(dismissText)
            }
        },
        title = { Text(title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold) },
        text = { Text(message) }
    )
}