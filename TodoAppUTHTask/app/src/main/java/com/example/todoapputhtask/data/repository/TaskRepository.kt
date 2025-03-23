package com.example.todoapputhtask.data.repository

import com.example.todoapputhtask.data.model.Task
import com.example.todoapputhtask.network.TaskApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TaskRepository {
    private val apiServices: TaskApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://amock.io/api/researchUTH/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApiService::class.java)
    }

    suspend fun getTasks(): List<Task> {
        val response = apiServices.getTasks()
        return response.data ?: emptyList()
    }
}