@file:OptIn(ExperimentalLayoutApi::class)

package com.aslansari.spiritvisor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aslansari.spiritvisor.home.home
import com.aslansari.spiritvisor.home.homeRoute

@Composable
internal fun SpiritVisorApp(
    viewModel: OrderViewModel = viewModel { OrderViewModel() },
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen

    Scaffold { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = homeRoute,
            modifier = Modifier.padding(innerPadding),
        ) {
            // Add destinations here
            home(
                navigateToDetail = {
                    navController.navigate("cocktail")
                }
            )
            composable(
                "cocktail",
            ) {
                Column {
                    Text("Cocktail Screen")
                    Button(onClick = { navController.navigateUp() }) {
                        Text("Go back")
                    }
                }
            }
        }
    }
}
