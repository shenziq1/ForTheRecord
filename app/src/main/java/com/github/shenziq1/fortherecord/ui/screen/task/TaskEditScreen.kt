package com.github.shenziq1.fortherecord.ui.screen.task

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
import com.github.shenziq1.fortherecord.ui.common.TopBackBar
import com.github.shenziq1.fortherecord.ui.theme.Blue300
import com.github.shenziq1.fortherecord.ui.theme.Blue400
import com.github.shenziq1.fortherecord.ui.viewmodel.task.TaskEditViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TaskEditScreen(
    onBackClicked: () -> Unit,
    viewModel: TaskEditViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val taskUiState = viewModel.taskUiState.collectAsState()

    var name by remember {
        mutableStateOf("")
    }
    var timeGoal by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = { TopBackBar(onClick = {onBackClicked()}) },
    ) {paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Let's change name to ")
            OutlinedTextField(
                value = name,
                singleLine = true,
                label = { Text(text = "name") },
                placeholder = { Text(text = taskUiState.value.name) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Next
                ),
                onValueChange = {
                    name = it
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
                placeholder = { Text(text = (taskUiState.value.timeGoal / 1000).toString()) },
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
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue300,
                    unfocusedBorderColor = Blue400
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                viewModel.editTask(name = name, timeGoal = timeGoal.toLong() * 1000)
                //viewModel.setNewTaskName(name = name)
                //viewModel.setNewTaskGoal(timeGoal = timeGoal.toLong() * 1000)
                onBackClicked()
            }) {
                Text(text = "save")
            }
        }

    }
}