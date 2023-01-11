package com.github.shenziq1.fortherecord.database

import com.github.shenziq1.fortherecord.database.Task

object FakeTask{
    var task = mutableListOf<Task>(
        Task(1, "test1"),
        Task(2, "test2"),
        Task(3, "test3"),
    )
}