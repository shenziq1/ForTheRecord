package com.github.shenziq1.fortherecord.ui.components.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.ui.common.TopBackBar
import com.github.shenziq1.fortherecord.ui.theme.Blue500
import com.github.shenziq1.fortherecord.ui.theme.Blue700
import com.github.shenziq1.fortherecord.viewmodel.TaskEntryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TaskNewScreen(
    navHostController: NavHostController,
    viewModel: TaskEntryViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val taskUiState = viewModel.taskUiState
    var name by remember {
        mutableStateOf("")
    }
    var timeGoal by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopBackBar(onClick = {navHostController.popBackStack()})
        }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Let's give it a name")
            OutlinedTextField(
                value = name,
                singleLine = true,
                label = { Text(text = "name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        coroutineScope.launch {
                            viewModel.updateUiState(taskUiState.copy(name = name))
                        }
                    }),
                onValueChange = {
                    name = it
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue700,
                    unfocusedBorderColor = Blue500
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
                        coroutineScope.launch {
                            viewModel.updateUiState(taskUiState.copy(timeGoal = timeGoal.toLong() * 1000))
                        }
                    }),
                onValueChange = {
                    timeGoal = it
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue700,
                    unfocusedBorderColor = Blue500
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                coroutineScope.launch {
                    viewModel.saveNewTask()
                    navHostController.popBackStack()
                }
            }) {
                Text(text = "save")
            }
        }
    }

}


















