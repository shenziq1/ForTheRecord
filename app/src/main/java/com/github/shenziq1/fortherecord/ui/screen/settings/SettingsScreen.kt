package com.github.shenziq1.fortherecord.ui.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.shenziq1.fortherecord.ui.common.TestText
import com.github.shenziq1.fortherecord.ui.navigation.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navHostController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navHostController = navHostController) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TestText(text = "Settings")
        }
    }

}