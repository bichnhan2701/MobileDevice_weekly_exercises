package com.example.appwithroomdatabase.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwithroomdatabase.data.Todo
import com.example.appwithroomdatabase.data.TodoDatabase
import com.example.appwithroomdatabase.domain.TodoRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository = TodoRepository(
        TodoDatabase.getDatabase(application).todoDao()
    )

    val allTodos: StateFlow<List<Todo>> = repository.allTodos
    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }
    fun update(todo: Todo) = viewModelScope.launch {
        repository.update(todo)
    }
    fun delete(todo: Todo) = viewModelScope.launch {
        repository.delete(todo)
    }
}