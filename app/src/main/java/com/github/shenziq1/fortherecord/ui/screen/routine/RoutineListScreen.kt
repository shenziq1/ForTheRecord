@file:OptIn(ExperimentalFoundationApi::class)

package com.github.shenziq1.fortherecord.ui.screen.routine

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.ui.common.SwipableTaskCard
import com.github.shenziq1.fortherecord.ui.common.Title
import com.github.shenziq1.fortherecord.ui.viewmodel.task.TaskListViewModel
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

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
                            onClick = { navHostController.navigate("RoutineNew") },
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
        val shuffled = taskList.toMutableList()
        val shuffled2 = remember{ mutableStateListOf<Task>() }
        shuffled2.clear()
        shuffled2.addAll(shuffled)
        when (taskList.size) {
            0 -> Text(text = "no content")
            else -> LazyColumn(
                Modifier
                    .padding(it)
                    ) {
                items(items = shuffled2, key = { task -> task.id }) { task ->

                    SwipableTaskCard(task = task, navHostController = navHostController)
                }
                item {
                    Button(onClick = {
                        shuffled.shuffle()
                        shuffled2.clear()
                        shuffled2.addAll(shuffled)
                        Log.d("shuffle", shuffled2[0].name)
                    }) {
                        Text(text = "shuffle")
                    }
                }
            }
        }
//        val data = remember { mutableStateOf(taskList) }
//        val state = rememberReorderableLazyListState(onMove = { from, to ->
//            data.value = data.value.toMutableList().apply {
//                add(to.index, removeAt(from.index))
//            }
//        })
//        LazyColumn(
//            state = state.listState,
//            modifier = Modifier
//                .reorderable(state)
//                .detectReorderAfterLongPress(state)
//                .padding(it)
//        ) {
//            items(data.value, { it}) { task ->
//                ReorderableItem(state, key = task.id) { isDragging ->
//                    val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
//                    Column(
//                        modifier = Modifier
//                            .shadow(elevation.value)
//                            .background(MaterialTheme.colors.surface)
//                    ) {
//                        Text(task.name)
//                    }
//                }
//            }
//        }
    }
}

