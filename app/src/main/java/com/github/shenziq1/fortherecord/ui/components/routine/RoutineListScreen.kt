package com.github.shenziq1.fortherecord.ui.components.routine

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.model.Task
import com.github.shenziq1.fortherecord.ui.common.TaskCard
import com.github.shenziq1.fortherecord.viewmodel.TaskUiState
import com.github.shenziq1.fortherecord.viewmodel.TaskViewModel

@Composable
fun RoutineListScreen(
    navHostController: NavHostController,
    viewModel: TaskViewModel = hiltViewModel()
) {

    when (viewModel.taskUiState) {
        is TaskUiState.SuccessTask ->
            Column {
                (viewModel.taskUiState as TaskUiState.SuccessTask).tasks.forEach() {

                    TaskCard(
                        task = Task(it.id, it.name),
                        navHostController = navHostController
                    )
                    Log.d("list item", "${it.id}")
                }

            }
        is TaskUiState.Error -> Text(text = "error")
        is TaskUiState.Loading -> Text(text = "loading")
    }

}

