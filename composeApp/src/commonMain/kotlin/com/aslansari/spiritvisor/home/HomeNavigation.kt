package com.aslansari.spiritvisor.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val homeRoute = "home"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.home(
    navigateToDetail: () -> Unit,
) {
    composable(homeRoute) {
        HomeRoute(
            navigateToDetail = navigateToDetail,
        )
    }
}
