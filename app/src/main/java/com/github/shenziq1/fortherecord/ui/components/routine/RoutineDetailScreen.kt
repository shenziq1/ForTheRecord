package com.github.shenziq1.fortherecord.ui.components.routine

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task

import com.github.shenziq1.fortherecord.ui.common.TestText2
import com.github.shenziq1.fortherecord.viewmodel.TaskUiState
import com.github.shenziq1.fortherecord.viewmodel.TaskViewModel

@Composable
fun RoutineDetailScreen(
    id: Int,
    navHostController: NavHostController,
    viewModel: TaskViewModel = hiltViewModel()
) {
//    viewModel.getTask(id)
//    when (viewModel.taskUiState) {
//        is TaskUiState.SuccessTask -> {
//
//
//            val task = (viewModel.taskUiState as TaskUiState.SuccessTask).tasks[0]
//            var taskName by remember { mutableStateOf(task.name) }
//            task.apply {
//                TestTaskDetail(
//                    task = Task(this.id, taskName),
//                    navHostController = navHostController,
//                    onClick = {taskName+='a'}
//                )
//                Log.d("list item", "${this.id}")
//
//
//            }
//        }
//        is TaskUiState.Error -> Text(text = "error")
//        is TaskUiState.Loading -> Text(text = "loading")
//    }
//
//}
//
//@Composable
//fun TestTaskDetail(task: Task, navHostController: NavHostController, onClick: () -> Unit) {
//
//    TestText2(
//        text = task.name,
//        buttonText = "Add a",
//        onClick = onClick,
//        modifier = Modifier.clickable { navHostController.popBackStack() })
//
//
}