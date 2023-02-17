@file:OptIn(ExperimentalFoundationApi::class)

package com.github.shenziq1.fortherecord.ui.screen.today

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.ui.screen.task.SwipableTaskCard
import com.github.shenziq1.fortherecord.ui.common.Title
import com.github.shenziq1.fortherecord.ui.viewmodel.today.TodayListViewModel

@Composable
fun TodayListScreen(
    onNewClicked: () -> Unit,
    navHostController: NavHostController,
    viewModel: TodayListViewModel = hiltViewModel()
) {
    val taskListUiState by viewModel.todayMapUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Title(text = "Today")
                        FloatingActionButton(
                            onClick = { onNewClicked() },
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
    { it ->
        val taskMap = taskListUiState.taskMap
        taskMap.forEach {
            Log.d("lazy", it.value.size.toString())
            Log.d("lazy", it.value.size.toString())
        }
        when (taskMap.size) {
            0 -> Text(text = "no content")
            else -> {
                LazyColumn(Modifier.padding(it)) {
                    taskMap.forEach { (category, tasks) ->
                        stickyHeader { Text(text = category) }
                        items(items = tasks, key = { task: Task -> task.id }) {
                            SwipableTaskCard(
                                task = it,
                                { navHostController.navigate("RoutineEdit/${it.id}") },
                                { navHostController.navigate("RoutineDetail/${it.id}")}
                            )
                        }
                    }
                }
            }
        }
    }
}

