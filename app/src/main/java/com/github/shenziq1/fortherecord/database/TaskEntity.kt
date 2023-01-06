package com.github.shenziq1.fortherecord.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.shenziq1.fortherecord.model.Task

@Entity(tableName = "taskEntity")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    //@ColumnInfo
    val name: String

)

fun TaskEntity.entityToTask(): Task{
    return Task(id, name)
}