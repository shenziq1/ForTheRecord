package com.github.shenziq1.fortherecord.ui.components.routine

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.ui.common.TopBackBar
import com.github.shenziq1.fortherecord.viewmodel.TaskDetailViewModel
import com.github.shenziq1.fortherecord.viewmodel.TaskEntryViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RoutineDetailScreen(
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
    Scaffold(topBar = { TopBackBar(navHostController = navHostController) }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = name, color = Color.Black)
//            OutlinedTextField(
//                value = name,
//                label = { Text(text = "name") },
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Ascii,
//                    imeAction = ImeAction.Done
//                ),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        keyboardController?.hide()
//                    }),
//                onValueChange = {
//                    name = it
//                    coroutineScope.launch {
//                        viewModel.updateUiState(TaskUiState(id = id, name = it))
//                    }
//                },
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = Blue700,
//                    unfocusedBorderColor = Blue500
//                )
//            )
//            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = {
                //focusManager.moveFocus(FocusDirection.Down)
                navHostController.navigate("RoutineEdit/${id}")
            }) {
                Text(text = "edit")
            }
            Button(onClick = { coroutineScope.launch {
                viewModel.deleteTask()
                navHostController.popBackStack()
            } }) {
                Text(text = "delete")
            }
        }

    }
}