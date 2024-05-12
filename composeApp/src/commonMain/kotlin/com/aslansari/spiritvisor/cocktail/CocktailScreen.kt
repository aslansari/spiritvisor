package com.aslansari.spiritvisor.cocktail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
internal fun CocktailRoute(
    navigateBack: () -> Unit,
    viewModel: CocktailViewModel = viewModel { CocktailViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    CocktailScreen(
        onBackClick = navigateBack,
        uiState = uiState
    )
}

@Composable
internal fun CocktailScreen(
    uiState: CocktailUIState,
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            // Add content here
            Text("Cocktail Screen")
            Button(onClick = onBackClick) {
                Text("Navigate to Home")
            }
        }
    }
}