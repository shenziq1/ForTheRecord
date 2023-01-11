package com.github.shenziq1.fortherecord.util
//
//import com.github.shenziq1.fortherecord.database.TaskEntity
//import com.github.shenziq1.fortherecord.model.Task
//
//interface Mapper<E, D> {
//    fun EtoD(e: E): D
//    fun DtoE(d: D): E
//}
//
//class TaskToEntityMapper: Mapper<TaskEntity, Task> {
//    override fun EtoD(e: TaskEntity): Task {
//        return Task(
//            id = e.id,
//            name = e.name
//        )
//    }
//
//    override fun DtoE(d: Task): TaskEntity {
//        return TaskEntity(
//            id = d.id,
//            name = d.name
//        )
//    }
//    companion object{
//        val taskToEntityMapper = TaskToEntityMapper()
//    }
//}