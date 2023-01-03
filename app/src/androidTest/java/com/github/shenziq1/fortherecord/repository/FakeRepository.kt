package com.github.shenziq1.fortherecord.repository

import com.github.shenziq1.fortherecord.database.FakeTask
import com.github.shenziq1.fortherecord.model.Task

class FakeRepository(): Repository{
    override fun getTasks(): List<Task> {
        return FakeTask.task
    }
}