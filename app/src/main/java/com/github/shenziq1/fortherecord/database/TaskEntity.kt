package com.github.shenziq1.fortherecord.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskTable")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    //@ColumnInfo
    val name: String,
    val category: String,
    val timeSpent: Long,
    val timeGoal: Long,
    val clickTimes: Int,
    val isRoutine: Boolean,
    val dayGoal: Int,
    val startingTime: Long,
    val endingTime: Long

)
