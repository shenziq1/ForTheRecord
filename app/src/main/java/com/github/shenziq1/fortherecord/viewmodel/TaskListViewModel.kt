package com.github.shenziq1.fortherecord.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.repository.OfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class TaskListUiState(val taskList: List<Task> = listOf())

@HiltViewModel
class TaskListViewModel @Inject constructor(offlineRepository: OfflineRepository) :
    ViewModel() {
    val taskListUiState: StateFlow<TaskListUiState> = offlineRepository.getAllTasks().map {
        TaskListUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = TaskListUiState()
    )
}
