package com.github.shenziq1.fortherecord.ui.viewmodel.insights

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

data class TaskMapUiState(val taskMap: Map<String, List<Task>> = mapOf())

@HiltViewModel
class InsightsViewModel @Inject constructor(private val offlineRepository: OfflineRepository) :
    ViewModel() {
    val routineMapUiState: StateFlow<TaskMapUiState> = offlineRepository.getAllRoutineReversed().map { tasks ->
        TaskMapUiState(tasks.groupBy { it.category })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = TaskMapUiState()
    )
    val todayMapUiState: StateFlow<TaskMapUiState> = offlineRepository.getAllTodayReversed().map { tasks ->
        TaskMapUiState(tasks.groupBy { it.category })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = TaskMapUiState()
    )
}