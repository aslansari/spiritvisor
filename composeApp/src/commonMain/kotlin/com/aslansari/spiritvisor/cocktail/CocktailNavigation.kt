package com.aslansari.spiritvisor.cocktail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import androidx.navigation.compose.composable

const val cocktailRoute = "cocktail_route"
const val categoryArg = "category"

data class CocktailArgs(
    val category: String,
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        category = checkNotNull(savedStateHandle[categoryArg])
    )
}

fun NavController.navigateToCocktail(category: String, navOptions: NavOptions? = null) {
    this.navigate(cocktailRoute.plus("/${category}"), navOptions)
}

fun NavGraphBuilder.cocktailScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = cocktailRoute.plus("/{${categoryArg}}"),
        arguments = listOf(
            navArgument(categoryArg) {
                type = NavType.StringType
            }
        )
    ) { backstackEntry ->
        val args = CocktailArgs(backstackEntry.savedStateHandle)
        CocktailRoute(
            args = args,
            navigateBack = navigateBack,
        )
    }
}