package com.github.shenziq1.fortherecord.repository

import com.github.shenziq1.fortherecord.database.FakeTask
import com.github.shenziq1.fortherecord.database.TaskDao
import com.github.shenziq1.fortherecord.model.Task
import com.github.shenziq1.fortherecord.util.TaskToEntityMapper

interface Repository {
    suspend fun getTasks(): List<Task>
}

class FakeRepository(): Repository{
    override suspend fun getTasks(): List<Task> {
        return FakeTask.task
    }
}

class OfflineRepository(val taskDao: TaskDao): Repository{
    override suspend fun getTasks(): List<Task> {
        val taskEntity = FakeTask.task.map { TaskToEntityMapper.taskToEntityMapper.DtoE(it) }
        taskEntity.forEach { taskDao.insert(it) }
        return taskDao.getAll().map { TaskToEntityMapper.taskToEntityMapper.EtoD(it) }
    }

}