package com.github.shenziq1.fortherecord.ui.common

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.ui.theme.Blue200
import com.github.shenziq1.fortherecord.ui.theme.Teal200
import com.github.shenziq1.fortherecord.viewmodel.TaskListViewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCard(
    task: Task,
    navHostController: NavHostController,
) {
    val route = "RoutineDetail/${task.id}"

    Card(
        onClick = { navHostController.navigate(route) },
        backgroundColor = Teal200,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = task.name, modifier = Modifier.padding(20.dp, 0.dp))
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipableTaskCard(
    task: Task,
    navHostController: NavHostController,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
//    coroutineScope.launch {
//        Log.d("viewmodel77", viewModel.taskListUiState.value.taskList.first().name)
//    }
    val currentTask by rememberUpdatedState(newValue = task)

    SwipeToDismiss(
        modifier = Modifier,
//        state = rememberDismissState(),
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
                        coroutineScope.launch {
                            viewModel.deleteTask(currentTask)
                        }
                        Log.d("viewmodel9", "I should be dismissed")
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
        TaskCard(task = task, navHostController = navHostController)
        Log.d("where", task.name)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableSample() {
    val width = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .background(Color.DarkGray)
        )
    }
}