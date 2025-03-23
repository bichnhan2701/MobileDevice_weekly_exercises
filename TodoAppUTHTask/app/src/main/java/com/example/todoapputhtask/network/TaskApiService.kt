package com.example.todoapputhtask.network

import com.example.todoapputhtask.data.model.Task
import retrofit2.http.GET

data class ApiRespone (
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)

interface TaskApiService{
    @GET("tasks")
    suspend fun getTasks(): ApiRespone
}