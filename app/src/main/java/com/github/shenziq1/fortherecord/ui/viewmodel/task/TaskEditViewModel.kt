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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskEditViewModel @Inject constructor(
    private val offlineRepository: OfflineRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val taskId = savedStateHandle.get<Int>("taskId")?:0

    var taskUiState: StateFlow<TaskUiState> =
        offlineRepository.getTask(taskId).filterNotNull().map {
            it.toTaskUiState()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = TaskUiState()
        )

    fun editTask(name: String, timeGoal: Long){
        val task = taskUiState.value.toTask().copy(name = name, timeGoal = timeGoal)
        viewModelScope.launch {
            offlineRepository.update(task)
        }
    }

    fun setNewTaskName(name: String){
        val task = taskUiState.value.toTask().copy(name = name)
        viewModelScope.launch {
            offlineRepository.update(task)
        }
    }

    fun setNewTaskGoal(timeGoal: Long){
        val task = taskUiState.value.toTask().copy(timeGoal = timeGoal)
        viewModelScope.launch {
            offlineRepository.update(task)
        }
    }
}

