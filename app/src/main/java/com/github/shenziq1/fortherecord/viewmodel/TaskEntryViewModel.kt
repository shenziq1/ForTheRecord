package com.github.shenziq1.fortherecord.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.repository.OfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TaskUiState(
    val id: Int = 0,
    val name: String = ""
)

fun TaskUiState.toTask(): Task {
    return Task(id = id, name = name)
}

fun Task.toTaskUiState(): TaskUiState {
    return TaskUiState(id = id, name = name)
}

@HiltViewModel
class TaskEntryViewModel @Inject constructor(
    private val offlineRepository: OfflineRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val taskId = savedStateHandle.get<Int>("taskId")?:0
    var taskUiState: TaskUiState by mutableStateOf(TaskUiState())
        private set

    fun updateUiState(newTaskUiState: TaskUiState) {
        taskUiState = newTaskUiState.copy()
    }

    private suspend fun getUiState(id: Int) {
        val name = offlineRepository.getTask(id).filterNotNull().first().name
        taskUiState = TaskUiState(id = id, name = name)

    }

    init {
        viewModelScope.launch {
            getUiState(taskId)
            Log.d("viewmodel10", taskUiState.name)
        }
    }

    suspend fun saveEditedTask() {
        if (taskUiState.isValid()) {
            offlineRepository.update(taskUiState.toTask())
            Log.d("update", taskUiState.toTask().name)
        }
    }

    suspend fun saveNewTask() {
        if (taskUiState.isValid()) {
            offlineRepository.insert(taskUiState.toTask())
            Log.d("insert", taskUiState.toTask().name)
        }
    }

    suspend fun deleteTask() {
        offlineRepository.delete(taskUiState.toTask())
        Log.d("delete", taskUiState.toTask().name)
    }
}

fun TaskUiState.isValid(): Boolean {
    return name.isNotBlank()
}