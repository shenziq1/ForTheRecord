package com.github.shenziq1.fortherecord.ui.viewmodel.task

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shenziq1.fortherecord.repository.OfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskNewViewModel @Inject constructor(
    private val offlineRepository: OfflineRepository
) : ViewModel() {

    var taskUiState: TaskUiState by mutableStateOf(TaskUiState())

    fun setNewTaskName(name: String){
        taskUiState = taskUiState.copy(name = name)
    }

    fun setNewTaskGoal(timeGoal: Long){
        taskUiState = taskUiState.copy(timeGoal = timeGoal)
    }

    fun setNewTaskCategory(category: String){
        taskUiState = taskUiState.copy(category = category)
    }

    fun saveNewTask() {
        viewModelScope.launch {
            offlineRepository.insert(taskUiState.toTask())
        }
    }
}
