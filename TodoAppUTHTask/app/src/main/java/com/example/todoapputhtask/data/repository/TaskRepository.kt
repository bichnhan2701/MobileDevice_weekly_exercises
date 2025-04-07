package com.example.todoapputhtask.data.repository

import com.example.todoapputhtask.data.model.Task
import com.example.todoapputhtask.network.TaskApiService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TaskRepository {
    private val fakeTaskList = mutableListOf<Task>() // RAM storage

    private val apiServices: TaskApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://amock.io/api/researchUTH/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApiService::class.java)
    }

//    suspend fun getTasks(): List<Task> {
//        val response = apiServices.getTasks()
//        return response.data ?: emptyList()
//    }
//
//    suspend fun postTask(task: Task): Response<Task> {
//        return apiServices.createTask(task)
//    }

    suspend fun getTasks(): List<Task> {
        // Kết hợp cả danh sách từ API và local RAM
        return try {
            val response = apiServices.getTasks()
            response.data.orEmpty() + fakeTaskList
        } catch (e: Exception) {
            fakeTaskList // fallback: chỉ lấy từ local nếu lỗi
        }
    }

    suspend fun postTask(task: Task): Response<Task> {
        // Giả lập task mới được thêm vào RAM
        fakeTaskList.add(task.copy(id = (1000..9999).random())) // giả ID random
        return Response.success(task)
    }
}