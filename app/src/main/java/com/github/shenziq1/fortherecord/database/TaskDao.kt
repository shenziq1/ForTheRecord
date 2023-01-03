package com.github.shenziq1.fortherecord.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskEntity")
    suspend fun getAll(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(marsPhotoEntity: TaskEntity)
}