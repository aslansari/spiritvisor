package com.aslansari.spiritvisor.cocktail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
internal fun CocktailRoute(
    args: CocktailArgs,
    navigateBack: () -> Unit,
    viewModel: CocktailViewModel = viewModel { CocktailViewModel() }
) {
    val argCategory by rememberUpdatedState(args)

    LaunchedEffect(argCategory) {
        viewModel.updateArgs(argCategory)
    }

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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Add content here
            AsyncImage(
                modifier = Modifier.size(256.dp).clip(CircleShape),
                model = "https://avatars.githubusercontent.com/u/12977501?v=4",
                contentDescription = null
            )
            Spacer(Modifier.size(24.dp))
            Text("Title is ${uiState.title}")
            Spacer(Modifier.size(12.dp))
            Text("Category is ${uiState.category}")
            Spacer(Modifier.size(12.dp))
            Button(onClick = onBackClick) {
                Text("Navigate to Home")
            }
        }
    }
}