package com.github.shenziq1.fortherecord.ui.screen.routine

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.ui.common.TopBackBar
import com.github.shenziq1.fortherecord.ui.theme.Blue300
import com.github.shenziq1.fortherecord.ui.theme.Blue400
import com.github.shenziq1.fortherecord.ui.viewmodel.task.TaskEditViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RoutineEditScreen(
    navHostController: NavHostController,
    viewModel: TaskEditViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val taskUiState = viewModel.taskUiState.collectAsState()

//    var name by remember {
//        mutableStateOf(taskUiState.value.name)
//    }
    var timeGoal by remember {
        mutableStateOf("")
    }

    Scaffold(topBar = { TopBackBar(onClick = { navHostController.popBackStack() }) }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Let's change name to ")
            OutlinedTextField(
                value = taskUiState.value.name,
                singleLine = true,
                label = { Text(text = "name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        keyboardController?.hide()
                    }),
                onValueChange = {
                    viewModel.setNewTaskName(name = it)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue300,
                    unfocusedBorderColor = Blue400
                )
            )
            Text(text = "Let's set a time goal")
            OutlinedTextField(
                value = timeGoal,
                singleLine = true,
                label = { Text(text = "goal") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }),
                onValueChange = {
                    timeGoal = it
                    viewModel.setNewTaskGoal(timeGoal = timeGoal.toLong() * 1000)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue300,
                    unfocusedBorderColor = Blue400
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                //focusManager.moveFocus(FocusDirection.Down)
                navHostController.popBackStack()
            }) {
                Text(text = "save")
            }
        }

    }
}