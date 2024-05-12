@file:OptIn(ExperimentalLayoutApi::class)

package com.aslansari.spiritvisor

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aslansari.spiritvisor.cocktail.cocktailScreen
import com.aslansari.spiritvisor.cocktail.navigateToCocktail
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
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.KeepUntilTransitionsFinished },
        ) {
            // Add destinations here
            home(navigateToDetail = navController::navigateToCocktail)
            cocktailScreen(navigateBack = navController::navigateUp)
        }
    }
}
