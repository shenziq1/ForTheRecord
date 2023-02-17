package com.github.shenziq1.fortherecord.ui.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.github.shenziq1.fortherecord.ui.screen.insights.InsightsScreen
import com.github.shenziq1.fortherecord.ui.screen.task.TaskListScreen
import com.github.shenziq1.fortherecord.ui.screen.routine.RoutineNewScreen
import com.github.shenziq1.fortherecord.ui.screen.settings.SettingsScreen
import com.github.shenziq1.fortherecord.ui.screen.task.TaskDetailScreen
import com.github.shenziq1.fortherecord.ui.screen.task.TaskEditScreen
import com.github.shenziq1.fortherecord.ui.screen.today.TodayNewScreen

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "Routine") {
        navigation(startDestination = "RoutineHome", route = "Routine") {
            composable("RoutineHome") {
                TaskListScreen(
                    destination = "Routine",
                    navHostController = navHostController
                )
            }
            composable("RoutineNew") {
                RoutineNewScreen(onBackClicked = { navHostController.popBackStack() })
            }
            composable(
                route = "RoutineDetail/{taskId}",
                arguments = listOf(navArgument("taskId") {
                    type = NavType.IntType
                }),
            ) {
                TaskDetailScreen(onBackClicked = { navHostController.popBackStack() })
            }
            composable(
                route = "RoutineEdit/{taskId}",
                arguments = listOf(navArgument("taskId") {
                    type = NavType.IntType
                })
            ) {
                TaskEditScreen(onBackClicked = { navHostController.popBackStack() })
            }

        }
        navigation(startDestination = "TodayHome", route = "Today") {
            composable("TodayHome") {
                TaskListScreen(
                    destination = "Today",
                    navHostController = navHostController
                )
            }
            composable("TodayNew") {
                TodayNewScreen(onBackClicked = { navHostController.popBackStack() })
            }
            composable(
                route = "TodayDetail/{taskId}",
                arguments = listOf(navArgument("taskId") {
                    type = NavType.IntType
                }),
            ) {
                TaskDetailScreen(onBackClicked = { navHostController.popBackStack() })
            }
            composable(
                route = "TodayEdit/{taskId}",
                arguments = listOf(navArgument("taskId") {
                    type = NavType.IntType
                })
            ) {
                TaskEditScreen(onBackClicked = { navHostController.popBackStack() })
            }

        }
        navigation(startDestination = "InsightsHome", route = "Insights") {
            composable("InsightsHome") {
                InsightsScreen(navHostController = navHostController)
            }
        }
        navigation(startDestination = "SettingsHome", route = "Settings") {
            composable("SettingsHome") {
                SettingsScreen(navHostController = navHostController)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navHostController: NavHostController) {
    val items = listOf(
        "Routine" to com.github.shenziq1.fortherecord.R.drawable.routine,
        "Today" to com.github.shenziq1.fortherecord.R.drawable.today,
        "Insights" to com.github.shenziq1.fortherecord.R.drawable.insights,
        "Settings" to com.github.shenziq1.fortherecord.R.drawable.settings
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    //Log.d("Navigation", "BottomNavigationBar: ${currentDestination?.hierarchy?.iterator()}")

    BottomNavigation() {
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any() { it.route == item.first } == true,
                onClick = { navHostController.navigate(item.first) },
                label = { Text(text = item.first) },
                icon = {
                    Icon(
                        //modifier = Modifier.size(10.dp),
                        painter = painterResource(id = item.second),
                        contentDescription = "",
                        //tint = Color.Black
                    )
                }
            )
        }
    }
}
