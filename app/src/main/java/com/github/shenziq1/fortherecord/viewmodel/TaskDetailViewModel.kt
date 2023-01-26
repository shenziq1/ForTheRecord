package com.github.shenziq1.fortherecord.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.shenziq1.fortherecord.repository.OfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val offlineRepository: OfflineRepository
) : ViewModel() {


    val id: Int? = savedStateHandle["id"]
    var taskUiState: TaskUiState by mutableStateOf(TaskUiState())
        private set
}