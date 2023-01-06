package com.github.shenziq1.fortherecord.model

import com.github.shenziq1.fortherecord.database.TaskEntity

data class Task(val id: Int, val name: String)

fun Task.taskToEntity():TaskEntity{
    return TaskEntity(id, name)
}
