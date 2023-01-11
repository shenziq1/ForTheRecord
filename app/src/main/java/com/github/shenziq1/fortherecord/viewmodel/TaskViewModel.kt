package com.github.shenziq1.fortherecord.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shenziq1.fortherecord.database.Task

import com.github.shenziq1.fortherecord.repository.OfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException
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

    suspend fun saveTask(){
        offlineRepository.insert(taskUiState.toTask())
    }
}