package com.github.shenziq1.fortherecord.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.shenziq1.fortherecord.database.Task

import com.github.shenziq1.fortherecord.repository.OfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class TaskUiState(
    val id: Int = 0,
    val name: String = ""
)

fun TaskUiState.toTask(): Task{
    return Task(id = id, name = name)
}

@HiltViewModel
class TaskViewModel @Inject constructor(private val offlineRepository: OfflineRepository): ViewModel(){
    var taskUiState: TaskUiState by mutableStateOf(TaskUiState())
        private set

    fun updateUiState(newTaskUiState: TaskUiState){
        taskUiState = newTaskUiState.copy()
    }

    suspend fun saveEditedTask(){
        if (taskUiState.isValid()){
            offlineRepository.update(taskUiState.toTask())
            Log.d("update", taskUiState.toTask().name)
        }
    }

    suspend fun saveNewTask(){
        if (taskUiState.isValid()){
            offlineRepository.insert(taskUiState.toTask())
            Log.d("insert", taskUiState.toTask().name)
        }
    }
}

fun TaskUiState.isValid(): Boolean{
    return name.isNotBlank()
}