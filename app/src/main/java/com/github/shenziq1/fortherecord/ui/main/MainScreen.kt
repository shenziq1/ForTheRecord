package com.github.shenziq1.fortherecord.ui.main

import android.content.Context
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.github.shenziq1.fortherecord.database.TaskDatabase
import com.github.shenziq1.fortherecord.repository.FakeRepository
import com.github.shenziq1.fortherecord.repository.OfflineRepository
import com.github.shenziq1.fortherecord.ui.components.routine.RoutineDetailScreen
import com.github.shenziq1.fortherecord.ui.components.routine.RoutineListScreen
import com.github.shenziq1.fortherecord.ui.components.routine.RoutineNewScreen
import com.github.shenziq1.fortherecord.ui.components.setting.SettingScreen
import com.github.shenziq1.fortherecord.ui.components.statictics.StatisticsScreen
import com.github.shenziq1.fortherecord.ui.components.today.TodayScreen

@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold (bottomBar = {BottomNavigationBar(navHostController = navHostController)}){
        NavHost(navController = navHostController, startDestination = "Routine"){
            navigation(startDestination = "RoutineHome", route = "Routine"){
                composable("RoutineHome"){
                    RoutineListScreen(navHostController = navHostController)
                }
                composable("RoutineNew"){
                    RoutineNewScreen(navHostController = navHostController)
                }
                composable(route = "RoutineDetail/{taskId}", arguments = listOf(navArgument("taskId"){
                    type = NavType.IntType
                })){
                    RoutineDetailScreen(id = (it.arguments?.getInt("taskId") ?: 0), navHostController = navHostController)
                }
            }
            navigation(startDestination = "TodayHome", route = "Today"){
                composable("TodayHome"){
                    TodayScreen(navHostController = navHostController)
                }
            }
            navigation(startDestination = "StatisticsHome", route = "Statistics"){
                composable("StatisticsHome"){
                    StatisticsScreen(navHostController = navHostController)
                }
            }
            navigation(startDestination = "SettingHome", route = "Setting"){
                composable("SettingHome"){
                    SettingScreen(navHostController = navHostController)
                }
            }
        }
    }

}

@Composable
fun BottomNavigationBar(navHostController: NavHostController) {
    val items = listOf("Routine", "Today", "Statistics", "Setting")
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation() {
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any() { it.route == item } == true,
                onClick = { navHostController.navigate(item) },
                label = {Text(text = item)},
                icon = {}
            )
        }
    }
}
