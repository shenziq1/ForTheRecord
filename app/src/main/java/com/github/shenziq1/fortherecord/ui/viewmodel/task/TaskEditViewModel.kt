package com.github.shenziq1.fortherecord.ui.viewmodel.task

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shenziq1.fortherecord.repository.OfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskEditViewModel @Inject constructor(
    private val offlineRepository: OfflineRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val taskId = savedStateHandle.get<Int>("taskId")?:0
    var taskUiState: TaskUiState by mutableStateOf(TaskUiState())
        private set

    private suspend fun getUiState(id: Int) {
        val task = offlineRepository.getTask(id).filterNotNull().first()
        taskUiState = task.toTaskUiState()

    }

    init {
        viewModelScope.launch {
            getUiState(taskId)
        }
    }

    fun setNewTaskName(name: String){
        taskUiState = taskUiState.copy(name = name)
    }

    fun setNewTaskGoal(timeGoal: Long){
        taskUiState = taskUiState.copy(timeGoal = timeGoal)
    }

    fun saveEditedTask() {
        viewModelScope.launch {
            if (taskUiState.isValid()) {
                offlineRepository.update(taskUiState.toTask())
            }
        }
    }
}

