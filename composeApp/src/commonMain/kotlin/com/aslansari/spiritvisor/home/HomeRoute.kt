@file:OptIn(ExperimentalLayoutApi::class)

package com.aslansari.spiritvisor.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
internal fun HomeRoute(
    navigateToDetail: () -> Unit,
    viewModel: HomeViewModel = viewModel { HomeViewModel() },
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        onClick = navigateToDetail,
        uiState = uiState,
    )
}

@Composable
internal fun HomeScreen(
    onClick: () -> Unit,
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Pick a flavor for your cocktail ...", style = MaterialTheme.typography.h4)
        Spacer(Modifier.size(32.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(.5f),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = 3,
        ) {
            FlavorCategoryButton("Sour", onClick = {})
            FlavorCategoryButton("Sweet", onClick = {})
            FlavorCategoryButton("Salty", onClick = {})
            FlavorCategoryButton("Spicy", onClick = {})
            FlavorCategoryButton("Bitter", onClick = { onClick() })
            FlavorCategoryButton("Herbal", onClick = {})
            FlavorCategoryButton("Fruity", onClick = {})
            FlavorCategoryButton("Smoky", onClick = {})
            FlavorCategoryButton("Umami", onClick = {})
        }
    }
}

@Composable
private fun RowScope.FlavorCategoryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(modifier = modifier.weight(1f), onClick = onClick) {
        Text(text)
    }
}
