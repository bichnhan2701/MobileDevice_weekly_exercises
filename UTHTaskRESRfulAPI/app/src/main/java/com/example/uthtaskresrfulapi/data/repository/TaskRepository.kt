package com.example.uthtaskresrfulapi.data.repository

import com.example.uthtaskresrfulapi.data.model.Task
import com.example.uthtaskresrfulapi.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TaskRepository {
    private val apiServices: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://amock.io/api/researchUTH/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    suspend fun getTasks(): List<Task> {
        val response = apiServices.getTasks()
        return response.data ?: emptyList()
    }
}