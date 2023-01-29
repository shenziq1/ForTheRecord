package com.github.shenziq1.fortherecord.ui.screen.today

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.shenziq1.fortherecord.database.Task
import com.github.shenziq1.fortherecord.ui.theme.Teal200
import kotlin.math.max

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GoalCard(task: Task) {
//    Card(
//        onClick = {
//        },
//        backgroundColor = Teal200,
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(80.dp),
//    ) {
//        Row(
//            modifier = Modifier,
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(text = task.name, modifier = Modifier.padding(20.dp, 0.dp))
//            Text(
//                text = (task.timeSpent / 1000).toString(),
//                modifier = Modifier.padding(20.dp, 0.dp)
//            )
//            Text(text = (task.timeGoal / 1000).toString(), modifier = Modifier.padding(20.dp, 0.dp))
//        }
//    }
    val ratio = max(1F - (task.timeSpent.toDouble() / task.timeGoal).toFloat(), 0F)
    Log.d("ratioTimeSpent", task.timeSpent.toString())
    Log.d("ratioTimeGoal", task.timeGoal.toString())
    Log.d("ratioDivide", (task.timeSpent.toDouble() / task.timeGoal).toString())
    Log.d("ratioRatio", ratio.toString())
    Box() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Card(
                onClick = {
                },
                backgroundColor = Teal200,
                modifier = Modifier
                    .fillMaxWidth(ratio)
                    .height(80.dp),
                shape = RoundedCornerShape(5, 0, 0, 5)
            ) {}
            Card(
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                shape = RoundedCornerShape(0, 5, 5, 0)
            ) {}
        }
        Column(
            modifier = Modifier.padding(20.dp, 0.dp)
                .fillMaxWidth()
                .height(80.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = task.name)
        }

    }

}