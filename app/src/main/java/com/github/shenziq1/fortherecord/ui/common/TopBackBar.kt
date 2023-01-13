package com.github.shenziq1.fortherecord.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TopBackBar(navHostController: NavHostController){
    TopAppBar(
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
//                FloatingActionButton(
//                    onClick = {navHostController.navigate("RoutineNew")},
//                    modifier = Modifier.size(36.dp)
//                ) {
//
//                }
            }
        },
        modifier = Modifier.fillMaxWidth(),
        //contentPadding = PaddingValues(20.dp, 0.dp)
    )
}