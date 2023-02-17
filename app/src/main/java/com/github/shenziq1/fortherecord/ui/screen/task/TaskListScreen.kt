@file:OptIn(ExperimentalFoundationApi::class)

package com.github.shenziq1.fortherecord.ui.screen.task

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.ui.common.TopTaskBar
import com.github.shenziq1.fortherecord.ui.navigation.BottomNavigationBar
import com.github.shenziq1.fortherecord.ui.viewmodel.task.TaskListViewModel
import kotlinx.coroutines.launch

@Composable
fun TaskListScreen(
    destination: String,
    navHostController: NavHostController,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    var taskMap: Map<String, List<Task>> = mapOf()
    var route = ""
    when (destination) {
        "Routine" -> {
            taskMap = viewModel.routineMapUiState.collectAsState().value.taskMap
            route = "RoutineNew"
        }
        "Today" -> {
            taskMap = viewModel.todayMapUiState.collectAsState().value.taskMap
            route = "TodayNew"
        }
    }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopTaskBar { navHostController.navigate(route) } },
        bottomBar = { BottomNavigationBar(navHostController = navHostController) },
        scaffoldState = scaffoldState
    )
    { it ->
        when (taskMap.size) {
            0 -> Text(text = "no content")
            else -> {
                LazyColumn(Modifier.padding(it)) {
                    taskMap.forEach { (category, tasks) ->
                        stickyHeader { Text(text = category) }
                        items(items = tasks, key = { task: Task -> task.id }) {
                            SwipableTaskCard(
                                task = it,
                                onDeleteSwiped = {
                                    coroutineScope.launch {
                                        val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                                            message = "undo delete?",
                                            actionLabel = "undo"
                                        )
                                        when (snackbarResult){
                                            SnackbarResult.ActionPerformed -> {viewModel.addTask(it)}
                                            SnackbarResult.Dismissed -> {}
                                        }
                                    }
                                },
                                onEditSwiped = { navHostController.navigate("RoutineEdit/${it.id}") },
                                onCardClicked = { navHostController.navigate("RoutineDetail/${it.id}") }
                            )
                        }
                    }
                }
            }
        }
    }
}

