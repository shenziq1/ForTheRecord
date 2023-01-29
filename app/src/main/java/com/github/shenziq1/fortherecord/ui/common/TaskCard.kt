package com.github.shenziq1.fortherecord.ui.common

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.ui.theme.Teal200
import com.github.shenziq1.fortherecord.ui.viewmodel.task.TaskListViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipableTaskCard(
    task: Task,
    navHostController: NavHostController,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    val currentTask by rememberUpdatedState(newValue = task)
    val route = "RoutineDetail/${currentTask.id}"
    val state = rememberDismissState(
        initialValue = DismissValue.Default,
        confirmStateChange = {
            when (it) {
                DismissValue.DismissedToEnd -> {
                    viewModel.deleteTask(currentTask)
                    Log.d("viewmodel9", "I should be dismissed")
                    true
                }
                DismissValue.DismissedToStart -> {
                    navHostController.navigate("RoutineEdit/${currentTask.id}")
                    Log.d("viewmodel9", "I am at edit now")
                    false
                }
                else -> {false}
            }
        })

    SwipeToDismiss(
        modifier = Modifier,
        state = state,
        directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
        dismissThresholds = {
            when (it) {
                DismissDirection.EndToStart -> FractionalThreshold(0.5f)
                DismissDirection.StartToEnd -> FractionalThreshold(0.5f)
                else -> FractionalThreshold(1f)
            }
        },
        background = {
            when (state.dismissDirection){
                DismissDirection.StartToEnd -> {
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp), backgroundColor = Color.Red) {
                        Row(Modifier.padding(20.dp, 0.dp),
                            verticalAlignment = Alignment.CenterVertically){
                            Icon(
                                modifier = Modifier.size(28.dp),
                                imageVector = Icons.Default.Delete,
                                contentDescription = "",
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(text = "Delete")
                        }
                    }
                }
                DismissDirection.EndToStart -> {
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp), backgroundColor = Color.Green) {
                        Row(Modifier.padding(20.dp, 0.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically){
                            Text(text = "Edit")
                            Spacer(modifier = Modifier.width(20.dp))
                            Icon(
                                modifier = Modifier.size(28.dp),
                                imageVector = Icons.Default.Edit,
                                contentDescription = "",
                                tint = Color.Black
                            )
                        }
                    }
                }
                else -> {}
            }
        }
    ) {
        Card(
            onClick = {
                navHostController.navigate(route)
                viewModel.updateTask(currentTask.copy(clickTimes = currentTask.clickTimes + 1))
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
                Text(
                    text = (task.timeSpent / 1000).toString(),
                    modifier = Modifier.padding(20.dp, 0.dp)
                )
                Text(text = (task.timeGoal / 1000).toString(), modifier = Modifier.padding(20.dp, 0.dp))
            }
        }
        Log.d("where", currentTask.name)
    }
}
