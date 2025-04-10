package com.example.todoapputhtask.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks  ORDER BY id DESC")
    fun getAllTasks(): Flow<List<LocalTaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: LocalTaskEntity)

    @Delete
    suspend fun delete(task: LocalTaskEntity)

    @Query("SELECT * FROM tasks WHERE id = :taskId LIMIT 1")
    suspend fun getTaskById(taskId: Int): LocalTaskEntity?
}
