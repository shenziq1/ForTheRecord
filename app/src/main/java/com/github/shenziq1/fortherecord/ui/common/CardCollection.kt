package com.github.shenziq1.fortherecord.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task

@Composable
fun CardCollection(tasks: List<Task>, navHostController: NavHostController) {
    Column() {
        Text(text = tasks[0].category)
        tasks.forEach {
            SwipableTaskCard(task = it, navHostController = navHostController)
        }
    }
}