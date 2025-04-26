package com.example.appwithroomdatabase.domain

import com.example.appwithroomdatabase.data.Todo
import com.example.appwithroomdatabase.data.TodoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.StateFlow

class TodoRepository(private val todoDao: TodoDao) {
    private val scope = CoroutineScope(Dispatchers.IO)

    val allTodos: StateFlow<List<Todo>> = todoDao.getAllTodos()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), emptyList())

    suspend fun insert(todo: Todo) = todoDao.insert(todo)
    suspend fun update(todo: Todo) = todoDao.update(todo)
    suspend fun delete(todo: Todo) = todoDao.delete(todo)
}
