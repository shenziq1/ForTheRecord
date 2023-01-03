package com.github.shenziq1.fortherecord.ui.components.routine

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.ui.common.TestText

@Composable
fun RoutineDetailScreen(id: Int, navHostController: NavHostController){
    TestText(text = "$id", modifier = Modifier.clickable { navHostController.popBackStack() })
}