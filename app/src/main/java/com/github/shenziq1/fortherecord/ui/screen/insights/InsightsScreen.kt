package com.github.shenziq1.fortherecord.ui.screen.insights

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.ui.viewmodel.insights.InsightsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InsightsScreen(
    navHostController: NavHostController,
    viewModel: InsightsViewModel = hiltViewModel()
) {
    val taskMap = viewModel.taskMapUiState.collectAsState().value.taskMap
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