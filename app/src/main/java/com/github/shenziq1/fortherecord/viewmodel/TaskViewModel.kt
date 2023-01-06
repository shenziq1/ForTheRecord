package com.github.shenziq1.fortherecord.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.github.shenziq1.fortherecord.model.Task
import com.github.shenziq1.fortherecord.repository.OfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface TaskUiState {
    data class SuccessTask(val tasks: List<Task>): TaskUiState
    object Error: TaskUiState
    object Loading: TaskUiState
}

@HiltViewModel
class TaskViewModel @Inject constructor(private val offlineRepository: OfflineRepository): ViewModel(){
    var taskUiState: TaskUiState by mutableStateOf(TaskUiState.Loading)
        private set

    init {
        getTasks()
    }

    private fun getTasks(){
        viewModelScope.launch {
            try {
                taskUiState = TaskUiState.SuccessTask(offlineRepository.getTasks())
            } catch (
                e: IOException
            ){
                TaskUiState.Error
            }
        }
    }

    fun getTask(taskId: Int){
        viewModelScope.launch {
            try {
                taskUiState = TaskUiState.SuccessTask(listOf(offlineRepository.getTask(taskId)))
            } catch (
                e: IOException
            ){
                TaskUiState.Error
            }
        }
    }

    private fun updateTask(taskId: Int){

    }
}