package com.aslansari.spiritvisor.cocktail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val cocktailRoute = "cocktail_route"

fun NavController.navigateToCocktail(navOptions: NavOptions? = null) {
    this.navigate(cocktailRoute, navOptions)
}

fun NavGraphBuilder.cocktailScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = cocktailRoute,
    ) {
        CocktailRoute(
            navigateBack = navigateBack,
        )
    }
}