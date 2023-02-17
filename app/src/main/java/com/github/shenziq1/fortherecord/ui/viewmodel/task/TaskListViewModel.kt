package com.github.shenziq1.fortherecord.ui.viewmodel.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.repository.OfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TaskMapUiState(val taskMap: Map<String, List<Task>> = mapOf())

@HiltViewModel
class TaskListViewModel @Inject constructor(private val offlineRepository: OfflineRepository) :
    ViewModel() {
val taskMapUiState: StateFlow<TaskMapUiState> = offlineRepository.getAllTasksReversed().map {
    TaskMapUiState(it.groupBy { it.category })
}.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000L),
    initialValue = TaskMapUiState()
)

    fun deleteTask(task: Task){
        viewModelScope.launch {
            offlineRepository.delete(task)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch {
            offlineRepository.update(task)
        }
    }
}
