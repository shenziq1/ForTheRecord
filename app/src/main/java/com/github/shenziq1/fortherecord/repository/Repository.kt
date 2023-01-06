package com.github.shenziq1.fortherecord.repository

import com.github.shenziq1.fortherecord.database.FakeTask
import com.github.shenziq1.fortherecord.database.TaskDao
import com.github.shenziq1.fortherecord.database.TaskEntity
import com.github.shenziq1.fortherecord.database.entityToTask
import com.github.shenziq1.fortherecord.model.Task
import com.github.shenziq1.fortherecord.util.TaskToEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface Repository {
    fun getAll(): Flow<List<Task>>
    fun getTask(taskId: Int): Flow<Task>
    suspend fun insert(taskEntity: TaskEntity)
    suspend fun delete(taskEntity: TaskEntity)
    suspend fun update(taskEntity: TaskEntity)

}

class FakeRepository(): Repository{
    override fun getAll(): Flow<List<Task>> {
        return flowOf(FakeTask.task)
    }

    override fun getTask(taskId: Int): Flow<Task> {
        return flowOf(FakeTask.task[taskId-1])
    }

    override suspend fun insert(taskEntity: TaskEntity) {
        FakeTask.task.add(taskEntity.entityToTask())
    }

    override suspend fun delete(taskEntity: TaskEntity) {
        FakeTask.task.remove(taskEntity.entityToTask())
    }

    override suspend fun update(taskEntity: TaskEntity) {
        for (item in FakeTask.task){
            if (item.id == taskEntity.id){
                FakeTask.task.remove(item)
                FakeTask.task.add(taskEntity.entityToTask())
            }
        }
    }
}

class OfflineRepository(val taskDao: TaskDao): Repository{
    override fun getAll(): Flow<List<Task>> {
        TODO("Not yet implemented")
    }



//    (): List<Task> {
//        val taskEntity = FakeTask.task.map { TaskToEntityMapper.taskToEntityMapper.DtoE(it) }
//        taskEntity.forEach { taskDao.insert(it) }
//        return taskDao.getAll().map { TaskToEntityMapper.taskToEntityMapper.EtoD(it) }
//    }

    override fun getTask(taskId: Int): Flow<Task> {
        return taskDao.getTask(taskId)
    }

    override suspend fun insert(taskEntity: TaskEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(taskEntity: TaskEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun update(taskEntity: TaskEntity) {
        TODO("Not yet implemented")
    }

}