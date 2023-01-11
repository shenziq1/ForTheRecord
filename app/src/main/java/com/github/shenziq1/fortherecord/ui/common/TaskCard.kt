package com.github.shenziq1.fortherecord.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.database.Task

import com.github.shenziq1.fortherecord.ui.theme.Teal200

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCard(task: Task, navHostController: NavHostController){
    val route = "RoutineDetail/${task.id}"
    Card(
        onClick = { navHostController.navigate(route) },
        backgroundColor = Teal200,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = task.name, modifier = Modifier.padding(20.dp, 0.dp))
        }
    }
}