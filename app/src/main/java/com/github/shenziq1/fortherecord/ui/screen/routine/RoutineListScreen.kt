package com.github.shenziq1.fortherecord.ui.screen.routine

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.ui.common.SwipableTaskCard
import com.github.shenziq1.fortherecord.ui.common.Title
import com.github.shenziq1.fortherecord.ui.viewmodel.task.TaskListViewModel

@Composable
fun RoutineListScreen(
    navHostController: NavHostController,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    val taskListUiState by viewModel.taskListUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Title(text = "Routine")
                        FloatingActionButton(
                            onClick = {navHostController.navigate("RoutineNew")},
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(20.dp, 0.dp)
            )
        },
    )
    {
        val taskList = taskListUiState.taskList
        when (taskList.size){
            0 -> Text(text = "no content")
            else -> LazyColumn() {
                items(items = taskList, key = {task -> task.id}) {
                    task -> SwipableTaskCard(task = task, navHostController =navHostController)
                }
            }
        }
    }
}

