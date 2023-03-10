package com.github.shenziq1.fortherecord.ui.screen.task

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.shenziq1.fortherecord.ui.common.TopBackBar
import com.github.shenziq1.fortherecord.ui.viewmodel.task.TaskDetailViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun TaskDetailScreen(
    onBackClicked: () -> Unit,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val taskUiState = viewModel.taskUiState.collectAsState()
    var timeSpent by remember {
        mutableStateOf(0L)
    }
    var singleTimeSpent by remember {
        mutableStateOf(0L)
    }
    var isRunning by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        TopBackBar(onClick = {
            when (isRunning) {
                true -> {
                    isRunning = false
                    viewModel.addTaskTimeSpent(timeSpent)
                    onBackClicked()
                }
                false -> {
                    onBackClicked()
                }
            }
        })
    }) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = taskUiState.value.name, fontSize = 28.sp)
            Text(text = (timeSpent / 1000L).toString(), fontSize = 72.sp)
            FloatingActionButton(onClick = {
                when (isRunning) {
                    true -> {
                        isRunning = false
                        viewModel.addTaskTimeSpent(singleTimeSpent)
                        singleTimeSpent = 0L

                    }
                    false -> {
                        isRunning = true
                        coroutineScope.launch {
                            delay(1000L)
                            while (isRunning) {
                                timeSpent += 1000L
                                singleTimeSpent += 1000L
                                delay(1000L)
                            }
                        }
                    }
                }

            }, modifier = Modifier.size(48.dp)) {
                when (isRunning) {
                    true -> Text(text = "Stop")
                    false -> Text(text = "Start")
                }
            }
        }

    }
}