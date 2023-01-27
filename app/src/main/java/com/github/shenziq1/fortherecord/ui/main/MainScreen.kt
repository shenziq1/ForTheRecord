package com.github.shenziq1.fortherecord.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.github.shenziq1.fortherecord.ui.components.goal.GoalScreen
import com.github.shenziq1.fortherecord.ui.components.setting.SettingScreen
import com.github.shenziq1.fortherecord.ui.components.statictics.StatisticsScreen
import com.github.shenziq1.fortherecord.ui.components.task.TaskDetailScreen
import com.github.shenziq1.fortherecord.ui.components.task.TaskEditScreen
import com.github.shenziq1.fortherecord.ui.components.task.TaskListScreen
import com.github.shenziq1.fortherecord.ui.components.task.TaskNewScreen

@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold(bottomBar = { BottomNavigationBar(navHostController = navHostController) }) { it ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(navController = navHostController, startDestination = "Task") {
                navigation(startDestination = "TaskHome", route = "Task") {
                    composable("TaskHome") {
                        TaskListScreen(navHostController = navHostController)
                    }
                    composable("TaskNew") {
                        TaskNewScreen(navHostController = navHostController)
                    }
                    composable(
                        route = "TaskDetail/{taskId}",
                        arguments = listOf(navArgument("taskId") {
                            type = NavType.IntType
                        })
                    ) {
                        TaskDetailScreen(
                            id = (it.arguments?.getInt("taskId") ?: 0),
                            navHostController = navHostController
                        )
                    }
                    composable(
                        route = "TaskEdit/{taskId}",
                        arguments = listOf(navArgument("taskId") {
                            type = NavType.IntType
                        })
                    ) {
                        TaskEditScreen(
                            id = (it.arguments?.getInt("taskId") ?: 0),
                            navHostController = navHostController
                        )
                    }

                }
                navigation(startDestination = "GoalHome", route = "Goal") {
                    composable("GoalHome") {
                        GoalScreen(navHostController = navHostController)
                    }
                }
                navigation(startDestination = "StatisticsHome", route = "Statistics") {
                    composable("StatisticsHome") {
                        StatisticsScreen(navHostController = navHostController)
                    }
                }
                navigation(startDestination = "SettingHome", route = "Setting") {
                    composable("SettingHome") {
                        SettingScreen(navHostController = navHostController)
                    }
                }
            }
        }
    }

}

@Composable
fun BottomNavigationBar(navHostController: NavHostController) {
    val items = listOf(
        "Task" to Icons.Default.Home,
        "Goal" to Icons.Default.Search,
        "Statistics" to Icons.Default.Favorite,
        "Setting" to Icons.Default.Settings
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation() {
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any() { it.route == item.first } == true,
                onClick = { navHostController.navigate(item.first) },
                label = { Text(text = item.first) },
                icon = { Icon(
                    //modifier = Modifier.size(10.dp),
                    imageVector = item.second,
                    contentDescription = "",
                    //tint = Color.Black
                )}
            )
        }
    }
}
