package com.example.uthtaskresrfulapi.network

import com.example.uthtaskresrfulapi.data.model.Task
import retrofit2.http.GET

data class ApiRespone (
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)

interface ApiService{
    @GET("tasks")
    suspend fun getTasks(): ApiRespone
}