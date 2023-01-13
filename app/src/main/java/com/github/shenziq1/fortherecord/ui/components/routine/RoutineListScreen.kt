package com.github.shenziq1.fortherecord.ui.components.routine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.ui.common.TaskCard
import com.github.shenziq1.fortherecord.ui.common.Title
import com.github.shenziq1.fortherecord.viewmodel.TaskListViewModel
import com.github.shenziq1.fortherecord.viewmodel.TaskViewModel

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
                        horizontalArrangement = Arrangement.SpaceBetween
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
            
//            else -> TaskCard(task = Task(taskList[0].id, taskList[0].name), navHostController = navHostController)
            else -> Column() {
                taskList.forEach { task ->
                    TaskCard(task = Task(task.id, task.name), navHostController =navHostController)
                }
            }

        }
        //TaskCard(task = Task(0, "test"), navHostController = navHostController)

    }
}

