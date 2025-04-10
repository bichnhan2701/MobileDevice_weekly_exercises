package com.example.todoapputhtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapputhtask.data.local.LocalTaskEntity
import com.example.todoapputhtask.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class AddTaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description.asStateFlow()

    private val _isAdded = MutableStateFlow(false)
    val isAdded: StateFlow<Boolean> = _isAdded.asStateFlow()

    private val _error = MutableStateFlow("")
    //val error: StateFlow<String> = _error.asStateFlow()

    fun onTitleChange(newTitle: String) {
        _title.value = newTitle
    }

    fun onDescriptionChange(newDescription: String) {
        _description.value = newDescription
    }

    fun addTaskToLocal() {
        val now = LocalDateTime.now().toString()

        val task = LocalTaskEntity(
            title = _title.value,
            description = _description.value,
            status = "Pending",
            priority = "Medium",
            category = "Work",
            dueDate = "2025-04-10",
            createdAt = now,
            updatedAt = now
        )

        viewModelScope.launch {
            try {
                repository.insertLocalTask(task)
                _isAdded.value = true
            } catch (e: Exception) {
                _error.value = "Lỗi khi lưu: ${e.localizedMessage}"
            }
        }
    }

    fun resetState() {
        _isAdded.value = false
        _error.value = ""
    }
}
