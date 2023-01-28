package com.github.shenziq1.fortherecord.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shenziq1.fortherecord.repository.OfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val offlineRepository: OfflineRepository
) : ViewModel() {

    private val taskId = savedStateHandle.get<Int>("taskId") ?: 0
    val taskUiState: StateFlow<TaskUiState> =
        offlineRepository.getTask(taskId).filterNotNull().map {
            it.toTaskUiState()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = TaskUiState()
        )

    suspend fun deleteTask() {
        offlineRepository.delete(taskUiState.value.toTask())
    }

    suspend fun updateTask(newTaskUiState: TaskUiState) {
        offlineRepository.update(newTaskUiState.toTask())
    }

}