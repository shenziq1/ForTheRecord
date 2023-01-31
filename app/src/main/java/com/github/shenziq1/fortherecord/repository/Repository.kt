package com.github.shenziq1.fortherecord.repository

//import com.github.shenziq1.fortherecord.database.FakeTask
import com.github.shenziq1.fortherecord.database.TaskDao
import com.github.shenziq1.fortherecord.database.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

interface Repository {
    fun getAllTasks(): Flow<List<Task>>
    fun getTask(taskId: Int): Flow<Task>

    fun getAllTasksReversed(): Flow<List<Task>>

    fun getTaskOrderByCategory(): Flow<List<Task>>

    fun getTaskGroupByCategory(): Flow<Map<Task, List<Task>>>
    suspend fun insert(task: Task)
    suspend fun delete(task: Task)
    suspend fun update(task: Task)

    suspend fun insertAll(tasks: List<Task>)

    suspend fun deleteAll(tasks: List<Task>)



}

//class FakeRepository(): Repository{
//    override fun getAllTasks(): Flow<List<Task>> {
//        return flowOf(FakeTask.task)
//    }
//
//    override fun getTask(taskId: Int): Flow<Task> {
//        return flowOf(FakeTask.task[taskId-1])
//    }
//
//    override suspend fun insert(task: Task) {
//        FakeTask.task.add(task)
//    }
//
//    override suspend fun delete(task: Task) {
//        FakeTask.task.remove(task)
//    }
//
//    override suspend fun update(task: Task) {
//        for (item in FakeTask.task){
//            if (item.id == task.id){
//                FakeTask.task.remove(item)
//                FakeTask.task.add(task)
//            }
//        }
//    }
//}

class OfflineRepository(val taskDao: TaskDao): Repository{
    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAll()
    }

    override fun getAllTasksReversed(): Flow<List<Task>> {
        return taskDao.getAllReversed()
    }
    override fun getTaskOrderByCategory(): Flow<List<Task>> {
        return taskDao.getAllOrderByCategory()
    }

    override fun getTaskGroupByCategory(): Flow<Map<Task, List<Task>>> {
        return taskDao.getAllGroupByCategory()
    }

//    (): List<Task> {
//        val taskEntity = FakeTask.task.map { TaskToEntityMapper.taskToEntityMapper.DtoE(it) }
//        taskEntity.forEach { taskDao.insert(it) }
//        return taskDao.getAll().map { TaskToEntityMapper.taskToEntityMapper.EtoD(it) }
//    }

    override fun getTask(taskId: Int): Flow<Task> {
        return taskDao.getTask(taskId)
    }

    override suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    override suspend fun insertAll(tasks: List<Task>) {
        tasks.forEach {
            taskDao.insert(it)
        }
    }

    override suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    override suspend fun deleteAll(tasks: List<Task>) {
        tasks.forEach {
            taskDao.delete(it)
        }
    }

    override suspend fun update(task: Task) {
        taskDao.update(task)
    }

}