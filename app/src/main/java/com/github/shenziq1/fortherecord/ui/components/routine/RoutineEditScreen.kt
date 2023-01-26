package com.github.shenziq1.fortherecord.ui.components.routine

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.ui.common.TopBackBar
import com.github.shenziq1.fortherecord.ui.theme.Blue500
import com.github.shenziq1.fortherecord.ui.theme.Blue700
import com.github.shenziq1.fortherecord.viewmodel.TaskUiState
import com.github.shenziq1.fortherecord.viewmodel.TaskEntryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RoutineEditScreen(
    id: Int,
    navHostController: NavHostController,
    viewModel: TaskEntryViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    Log.d("viewmodel2", viewModel.taskUiState.name)
    Log.d("viewmodel2", viewModel.taskUiState.id.toString())
    var name = viewModel.taskUiState.name

    Scaffold(topBar = { TopBackBar(navHostController = navHostController) }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = name, color = Color.Black)
            Text(text = "Above should be a ")
            OutlinedTextField(
                value = name,
                label = { Text(text = "name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }),
                onValueChange = {
                    name = it
                    coroutineScope.launch {
                        viewModel.updateUiState(TaskUiState(id = id, name = it))
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue700,
                    unfocusedBorderColor = Blue500
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                //focusManager.moveFocus(FocusDirection.Down)
                coroutineScope.launch {
                    viewModel.saveEditedTask()
                }
                navHostController.popBackStack()
            }) {
                Text(text = "save")
            }
        }

    }
}