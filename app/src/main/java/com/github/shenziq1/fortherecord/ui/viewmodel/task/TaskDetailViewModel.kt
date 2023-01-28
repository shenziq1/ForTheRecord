package com.github.shenziq1.fortherecord.ui.viewmodel.task

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shenziq1.fortherecord.repository.OfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val offlineRepository: OfflineRepository
) : ViewModel() {

    private val taskId = savedStateHandle.get<Int>("taskId") ?: 0
    var taskUiState: StateFlow<TaskUiState> =
        offlineRepository.getTask(taskId).filterNotNull().map {
            it.toTaskUiState()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = TaskUiState()
        )

    fun addTaskTimeSpent(timeSpent: Long){
        viewModelScope.launch {
            val task = taskUiState.value.toTask()
            offlineRepository.update(task.copy(timeSpent = task.timeSpent + timeSpent))
        }
    }


}