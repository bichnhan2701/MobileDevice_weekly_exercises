package com.example.todoapputhtask.data.repository

import com.example.todoapputhtask.data.local.LocalTaskEntity
import com.example.todoapputhtask.data.local.TaskDao
import com.example.todoapputhtask.data.model.Task
import com.example.todoapputhtask.network.TaskApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TaskRepository(
    private val taskDao: TaskDao
) {

    private val apiServices: TaskApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://amock.io/api/researchUTH/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApiService::class.java)
    }

    // Lấy task từ API
    suspend fun getTasksFromApi(): List<Task> {
        return try {
            val response = apiServices.getTasks()
            response.data ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Task local (Room)
    fun getLocalTasks(): Flow<List<LocalTaskEntity>> = taskDao.getAllTasks()

    suspend fun insertLocalTask(task: LocalTaskEntity) {
        taskDao.insert(task)
    }

    suspend fun deleteLocalTask(task: LocalTaskEntity) {
        taskDao.delete(task)
    }

    // Nếu sau này muốn gọi API tạo task, giữ lại
//    suspend fun postTask(task: Task): Response<Task> {
//        return apiServices.createTask(task)
//    }
}