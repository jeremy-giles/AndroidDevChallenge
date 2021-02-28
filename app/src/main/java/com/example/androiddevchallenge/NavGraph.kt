package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.androiddevchallenge.MainDestinations.DETAIL_ID_KEY
import com.example.androiddevchallenge.model.villagers

object MainDestinations {
    const val LIST_ROUTE = "list"
    const val DETAIL_ROUTE = "detail"
    const val DETAIL_ID_KEY = "detailId"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.LIST_ROUTE) {
    val navController = rememberNavController()

    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination) {
        composable(MainDestinations.LIST_ROUTE) {
            Villagers(villagers = villagers.sortedBy { it.name }, selectVillager = actions.selectVillager)
        }
        composable("${MainDestinations.DETAIL_ROUTE}/{$DETAIL_ID_KEY}",
        arguments = listOf(navArgument(DETAIL_ID_KEY) { type = NavType.LongType })
            ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            VillagerDetail(
                villagerId = arguments.getLong(DETAIL_ID_KEY)
            )

        }
    }
}

class MainActions(navController: NavHostController) {
    val selectVillager: (Long) -> Unit = { villagerId: Long ->
        navController.navigate("${MainDestinations.DETAIL_ROUTE}/$villagerId")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}