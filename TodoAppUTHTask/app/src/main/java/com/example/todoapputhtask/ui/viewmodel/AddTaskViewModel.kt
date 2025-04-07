package com.example.todoapputhtask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapputhtask.data.model.*
import com.example.todoapputhtask.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class AddTaskViewModel : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description.asStateFlow()

    private val _status = MutableStateFlow("Pending") // hoặc enum nếu cần
    private val _priority = MutableStateFlow("Medium")
    private val _category = MutableStateFlow("Work")
    private val _dueDate = MutableStateFlow("2025-04-10") // tạm hardcode
    private val _subtasks = MutableStateFlow(listOf<Subtask>())
    private val _attachments = MutableStateFlow(listOf<Attachment>())
    private val _reminders = MutableStateFlow(listOf<Reminder>())

    private val _isAdded = MutableStateFlow(false)
    val isAdded: StateFlow<Boolean> = _isAdded.asStateFlow()

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error.asStateFlow()

    fun onTitleChange(newTitle: String) {
        _title.value = newTitle
    }

    fun onDescriptionChange(newDescription: String) {
        _description.value = newDescription
    }

    val now = LocalDateTime.now().toString()

    fun addTask() {
        val task = Task(
            id = 0, // server sẽ tạo
            title = _title.value,
            description = _description.value,
            status = _status.value,
            priority = _priority.value,
            category = _category.value,
            dueDate = _dueDate.value,
            createdAt = now,
            updatedAt = now,
            subtasks = _subtasks.value,
            attachments = _attachments.value,
            reminders = _reminders.value
        )

        viewModelScope.launch {
            try {
                val response = TaskRepository.postTask(task)
                if (response.isSuccessful) {
                    _isAdded.value = true
                } else {
                    _error.value = "Lỗi khi thêm task: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Lỗi: ${e.localizedMessage}"
            }
        }
    }

    fun resetState() {
        _isAdded.value = false
        _error.value = ""
    }
}
