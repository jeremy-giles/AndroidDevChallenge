/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.MainDestinations.DETAIL_ID_KEY
import com.example.androiddevchallenge.model.villagers
import com.example.androiddevchallenge.ui.villager.VillagerDetail
import com.example.androiddevchallenge.ui.villagers.Villagers

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
        startDestination = startDestination
    ) {
        composable(MainDestinations.LIST_ROUTE) {
            Villagers(villagers = villagers.sortedBy { it.name }, selectVillager = actions.selectVillager)
        }
        composable(
            "${MainDestinations.DETAIL_ROUTE}/{$DETAIL_ID_KEY}",
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
}
