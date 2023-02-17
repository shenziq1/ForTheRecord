package com.github.shenziq1.fortherecord.ui.screen.routine

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
import com.github.shenziq1.fortherecord.ui.theme.Blue300
import com.github.shenziq1.fortherecord.ui.theme.Blue400

import com.github.shenziq1.fortherecord.ui.viewmodel.task.TaskNewViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RoutineNewScreen(
    navHostController: NavHostController,
    viewModel: TaskNewViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var name by remember {
        mutableStateOf("")
    }
    var timeGoal by remember {
        mutableStateOf("")
    }

    var category by remember{
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopBackBar(onClick = {navHostController.popBackStack()})
        }) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
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
                    imeAction = ImeAction.Next
                ),
                onValueChange = {
                    name = it
                    viewModel.setNewTaskName(name = name)
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
                    imeAction = ImeAction.Next
                ),

                onValueChange = {
                    timeGoal = it
                    viewModel.setNewTaskGoal(timeGoal = timeGoal.toLong() * 1000)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue300,
                    unfocusedBorderColor = Blue400
                )
            )
            Text(text = "Let's give it a category")
            OutlinedTextField(
                value = category,
                singleLine = true,
                label = { Text(text = "category") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }),

                onValueChange = {
                    category = it
                    viewModel.setNewTaskCategory(category = category)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue300,
                    unfocusedBorderColor = Blue400
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                viewModel.saveNewTask()
                navHostController.popBackStack()
            }) {
                Text(text = "save")
            }
        }
    }

}


















