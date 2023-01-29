package com.github.shenziq1.fortherecord.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.github.shenziq1.fortherecord.ui.screen.today.GoalListScreen
import com.github.shenziq1.fortherecord.ui.screen.settings.SettingsScreen
import com.github.shenziq1.fortherecord.ui.screen.insights.InsightsScreen
import com.github.shenziq1.fortherecord.ui.screen.routine.RoutineDetailScreen
import com.github.shenziq1.fortherecord.ui.screen.routine.RoutineEditScreen
import com.github.shenziq1.fortherecord.ui.screen.routine.RoutineListScreen
import com.github.shenziq1.fortherecord.ui.screen.routine.RoutineNewScreen

@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold(bottomBar = { BottomNavigationBar(navHostController = navHostController) }) { it ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(navController = navHostController, startDestination = "Routine") {
                navigation(startDestination = "RoutineHome", route = "Routine") {
                    composable("RoutineHome") {
                        RoutineListScreen(navHostController = navHostController)
                    }
                    composable("RoutineNew") {
                        RoutineNewScreen(navHostController = navHostController)
                    }
                    composable(
                        route = "RoutineDetail/{taskId}",
                        arguments = listOf(navArgument("taskId") {
                            type = NavType.IntType
                        })
                    ) {
                        RoutineDetailScreen(navHostController = navHostController)
                    }
                    composable(
                        route = "RoutineEdit/{taskId}",
                        arguments = listOf(navArgument("taskId") {
                            type = NavType.IntType
                        })
                    ) {
                        RoutineEditScreen(navHostController = navHostController)
                    }

                }
                navigation(startDestination = "TodayHome", route = "Today") {
                    composable("TodayHome") {
                        GoalListScreen(navHostController = navHostController)
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

    BottomNavigation() {
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any() { it.route == item.first } == true,
                onClick = { navHostController.navigate(item.first) },
                label = { Text(text = item.first) },
                icon = { Icon(
                    //modifier = Modifier.size(10.dp),
                    painter = painterResource(id = item.second),
                    contentDescription = "",
                    //tint = Color.Black
                )}
            )
        }
    }
}
