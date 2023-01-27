package com.github.shenziq1.fortherecord.ui.components.task

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.ui.common.TopBackBar
import com.github.shenziq1.fortherecord.viewmodel.TaskDetailViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun TaskDetailScreen(
    id: Int,
    navHostController: NavHostController,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val taskUiState = viewModel.taskUiState.collectAsState()
    var timeLeft by remember {
        mutableStateOf(60000L)
    }
    var isRunning by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = { TopBackBar(onClick = { navHostController.popBackStack() }) }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = taskUiState.value.name)
            Text(text = (timeLeft/1000L).toString(), fontSize = 36.sp)
            FloatingActionButton(onClick = {
                when(isRunning){
                    true -> {
                        isRunning = false
                    }
                    false -> {
                        isRunning = true
                        coroutineScope.launch {
                            while (timeLeft > 0L && isRunning){
                                delay(100L)
                                timeLeft -= 100L
                            }
                            isRunning = false
                        }
                    }
                }

            }, modifier = Modifier.size(48.dp)) {
                when(isRunning){
                    true -> Text(text = "Stop")
                    false -> Text(text = "Start")
                }

                //Icon(imageVector = Icons.Default.Settings, contentDescription = "")
            }


//            Button(onClick = { coroutineScope.launch {
//                viewModel.deleteTask()
//                navHostController.popBackStack()
//            } }) {
//                Text(text = "delete")
//            }
        }

    }
}