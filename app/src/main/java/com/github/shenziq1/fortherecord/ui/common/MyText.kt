package com.github.shenziq1.fortherecord.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun TestText(text: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = text, fontSize = 48.sp)
    }
}

@Composable
fun Title(text: String, modifier: Modifier = Modifier){
    Text(text = text, fontSize = 24.sp, color = Color.White)
}

@Composable
fun TestText2(text: String, buttonText: String, onClick: ()->Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = text, fontSize = 48.sp)
        Button(onClick = onClick) {
            Text(text = buttonText)
        }
    }
}

@Preview
@Composable
fun TestTestText() {
    TestText(text = "test")
}