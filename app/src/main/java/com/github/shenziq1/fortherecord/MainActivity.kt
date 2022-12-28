package com.github.shenziq1.fortherecord

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.shenziq1.fortherecord.ui.main.MainScreen
import com.github.shenziq1.fortherecord.ui.theme.ForTheRecordTheme

class MainActivity : ComponentActivity() {
    lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForTheRecordTheme {
                navHostController = rememberNavController()
                // A surface container using the 'background' color from the theme
                MainScreen(navHostController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ForTheRecordTheme {
        //Greeting("Android")
    }
}