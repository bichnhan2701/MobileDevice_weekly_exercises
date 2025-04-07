package com.example.todoapputhtask.network

import com.example.todoapputhtask.data.model.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class ApiRespone (
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)

interface TaskApiService{
    @GET("tasks")
    suspend fun getTasks(): ApiRespone

    @POST("tasks")
    suspend fun createTask(@Body task: Task): Response<Task>
}