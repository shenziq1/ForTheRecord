package com.github.shenziq1.fortherecord.ui.viewmodel.task

import com.github.shenziq1.fortherecord.database.Task

data class TaskUiState(
    val id: Int = 0,
    var name: String = "",
    val category: String = "",
    val timeSpent: Long = 0L,
    val timeGoal: Long = 0L,
    val clickTimes: Int = 0,
    val startingTime: Long = 0L,
    val endingTime: Long = 0L
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

fun TaskUiState.isValid(): Boolean {
    return name.isNotBlank()
}
