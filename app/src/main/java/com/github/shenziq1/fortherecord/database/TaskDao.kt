package com.github.shenziq1.fortherecord.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskTable")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM taskTable ORDER BY id DESC")
    fun getAllReversed(): Flow<List<Task>>

    @Query("SELECT * FROM taskTable ORDER BY category")
    fun getAllOrderByCategory(): Flow<List<Task>>

    @Query("SELECT * FROM taskTable GROUP BY category")
    fun getAllGroupByCategory(): Flow<Map<Task, List<Task>>>

    @Query("SELECT * FROM taskTable WHERE id == :id")
    fun getTask(id: Int): Flow<Task>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)
}