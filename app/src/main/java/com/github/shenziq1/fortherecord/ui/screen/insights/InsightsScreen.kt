package com.github.shenziq1.fortherecord.ui.screen.insights

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.ui.common.Title
import com.github.shenziq1.fortherecord.ui.viewmodel.insights.InsightsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InsightsScreen(
    viewModel: InsightsViewModel = hiltViewModel()
) {

    var taskMap:Map<String, List<Task>> = mapOf()
    val tabs = listOf("Routine", "Today")
    var selected by remember{ mutableStateOf(0) }
    when (selected){
        0 -> taskMap = viewModel.routineMapUiState.collectAsState().value.taskMap
        1 -> taskMap = viewModel.todayMapUiState.collectAsState().value.taskMap
    }

    Column() {
        TabRow(modifier = Modifier.height(58.dp), selectedTabIndex = selected) {
            tabs.forEachIndexed() { index, title ->
                Tab(selected = index == selected, onClick = { selected = index}) {
                    Title(text = title)
                }
            }
        }
        LazyColumn() {
            taskMap.forEach { (category, tasks) ->
                stickyHeader(){
                    Text(text = category)
                }
                items(items = tasks, key = {it.id}){
                    Column() {
                        Text(text = "name ${it.name}")
                        Text(text = "clickTimes ${it.clickTimes}")
                        Text(text = "timeGoal${it.timeGoal}")
                        Text(text = "timeSpent${it.timeSpent}")
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }
    }



}