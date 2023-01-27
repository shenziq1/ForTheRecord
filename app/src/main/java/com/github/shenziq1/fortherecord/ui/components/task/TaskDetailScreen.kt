package com.github.shenziq1.fortherecord.ui.components.task

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.ui.common.TestText
import com.github.shenziq1.fortherecord.ui.common.TopBackBar
import com.github.shenziq1.fortherecord.viewmodel.TaskDetailViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TaskDetailScreen(
    id: Int,
    navHostController: NavHostController,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val taskUiState = viewModel.taskUiState.collectAsState()
    Log.d("viewmodel2", taskUiState.value.name)

//    coroutineScope.launch {
//        viewModel.getUiState(id)
//    }
    val name = taskUiState.value.name
    Scaffold(topBar = { TopBackBar(onClick = {navHostController.popBackStack()}) }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TestText(text = name)
//            Button(onClick = {
//                //focusManager.moveFocus(FocusDirection.Down)
//                navHostController.navigate("TaskEdit/${id}")
//            }) {
//                Text(text = "edit")
//            }
//            Button(onClick = { coroutineScope.launch {
//                viewModel.deleteTask()
//                navHostController.popBackStack()
//            } }) {
//                Text(text = "delete")
//            }
        }

    }
}