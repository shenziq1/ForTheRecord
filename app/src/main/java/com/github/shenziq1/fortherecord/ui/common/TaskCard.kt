package com.github.shenziq1.fortherecord.ui.common

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.ui.theme.Blue50
import com.github.shenziq1.fortherecord.ui.theme.Orange300
import com.github.shenziq1.fortherecord.ui.theme.Teal100
import com.github.shenziq1.fortherecord.ui.theme.Teal200
import com.github.shenziq1.fortherecord.ui.viewmodel.task.TaskListViewModel
import kotlin.math.max
import kotlin.math.roundToInt

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
                    true
                }
                DismissValue.DismissedToStart -> {
                    navHostController.navigate("RoutineEdit/${currentTask.id}")
                    false
                }
                else -> {false}
            }
        })
    var offsetY by remember { mutableStateOf(0f) }
    SwipeToDismiss(
        modifier = Modifier,
//            .offset { IntOffset(0, offsetY.roundToInt()) }
//            .draggable(
//                orientation = Orientation.Vertical,
//                state = rememberDraggableState { delta ->
//                    offsetY += delta
//                }
//            ),
//        modifier = Modifier,

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
                        .height(80.dp), backgroundColor = Orange300) {
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
                        .height(80.dp), backgroundColor = Teal100) {
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
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            shape = RoundedCornerShape(5)
        ) {
            val ratio = max(1F - (task.timeSpent.toDouble() / task.timeGoal).toFloat(), 0F)
            Row{
                Card(
                    modifier = Modifier
                        .fillMaxWidth(ratio)
                        .height(80.dp),
                    shape = RoundedCornerShape(5)
                ) {}
                Card(
                    backgroundColor = Blue50,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    shape = RoundedCornerShape(0, 5, 5, 0)
                ) {}
            }
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
    }
}
