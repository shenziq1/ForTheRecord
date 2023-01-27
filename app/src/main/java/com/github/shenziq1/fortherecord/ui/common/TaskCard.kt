package com.github.shenziq1.fortherecord.ui.common

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.ui.theme.Teal200
import com.github.shenziq1.fortherecord.viewmodel.TaskListViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipableTaskCard(
    task: Task,
    navHostController: NavHostController,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val currentTask by rememberUpdatedState(newValue = task)
    val route = "TaskDetail/${currentTask.id}"

    SwipeToDismiss(
        modifier = Modifier,
        state = rememberDismissState(
            initialValue = DismissValue.Default,
            confirmStateChange = {
                when (it) {
                    DismissValue.DismissedToEnd -> {
                        coroutineScope.launch {
                            viewModel.deleteTask(currentTask)
                        }
                        Log.d("viewmodel9", "I should be dismissed")
                    }
                    DismissValue.DismissedToStart -> {
                        navHostController.navigate("TaskEdit/${currentTask.id}")
                        Log.d("viewmodel9", "I am at edit now")
                    }
                    else -> {}
                }
                true
            }),
        directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
        dismissThresholds = {
            when (it) {
                DismissDirection.EndToStart -> FractionalThreshold(0.5f)
                DismissDirection.StartToEnd -> FractionalThreshold(0.5f)
                else -> FractionalThreshold(1f)
            }
        },
        background = {
        }
    ) {
        Card(
            onClick = {
                navHostController.navigate(route)
                coroutineScope.launch {
                    viewModel.updateTask(currentTask.copy(clickTimes = currentTask.clickTimes + 1))
                }
            },
            backgroundColor = Teal200,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = currentTask.name, modifier = Modifier.padding(20.dp, 0.dp))
                Text(text = currentTask.clickTimes.toString(), modifier = Modifier.padding(20.dp, 0.dp))
            }
        }
        Log.d("where", currentTask.name)
    }
}
