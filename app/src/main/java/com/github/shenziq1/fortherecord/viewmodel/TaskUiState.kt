package com.github.shenziq1.fortherecord.viewmodel

import com.github.shenziq1.fortherecord.database.Task

data class TaskUiState(
    val id: Int = 0,
    var name: String = "",
    val category: String = "",
    val timeSpent: Int = 0,
    val timeGoal: Int = 0,
    val clickTimes: Int = 0,
    val startingTime: Int = 0,
    val endingTime: Int = 0
)

fun TaskUiState.toTask(): Task {
    return Task(
        id = id,
        name = name,
        category = category,
        timeSpent = timeSpent,
        timeGoal = timeGoal,
        clickTimes = clickTimes,
        startingTime = startingTime,
        endingTime = endingTime
    )
}

fun Task.toTaskUiState(): TaskUiState {
    return TaskUiState(
        id = id,
        name = name,
        category = category,
        timeSpent = timeSpent,
        timeGoal = timeGoal,
        clickTimes = clickTimes,
        startingTime = startingTime,
        endingTime = endingTime
    )
}
