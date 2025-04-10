package com.example.todoapputhtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapputhtask.data.local.LocalTaskEntity
import com.example.todoapputhtask.data.model.Task
import com.example.todoapputhtask.data.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    val tasks: StateFlow<List<LocalTaskEntity>> = repository.getLocalTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun loadTasksFromApi() {
        viewModelScope.launch {
            val remoteTasks = repository.getTasksFromApi()
            remoteTasks.forEach { task ->
                val entity = task.toLocalTaskEntity()
                repository.insertLocalTask(entity)
            }
        }
    }

//    fun deleteTask(task: LocalTaskEntity) {
//        viewModelScope.launch {
//            repository.deleteLocalTask(task)
//        }
//    }
}

// 🔁 Extension để chuyển từ Task -> LocalTaskEntity
fun Task.toLocalTaskEntity(): LocalTaskEntity {
    return LocalTaskEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        status = this.status,
        priority = this.priority,
        category = this.category,
        dueDate = this.dueDate,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}