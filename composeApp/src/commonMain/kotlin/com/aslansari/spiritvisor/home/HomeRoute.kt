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
    navigateToDetail: (String) -> Unit,
    viewModel: HomeViewModel = viewModel {
        HomeViewModel()
    },
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        onClick = navigateToDetail,
        uiState = uiState,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun HomeScreen(
    onClick: (String) -> Unit,
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
            FlavorCategoryButton("Sour", onClick = { onClick("Sour") })
            FlavorCategoryButton("Sweet", onClick = { onClick("Sweet") })
            FlavorCategoryButton("Salty", onClick = { onClick("Salty") })
            FlavorCategoryButton("Spicy", onClick = { onClick("Spicy") })
            FlavorCategoryButton("Bitter", onClick = { onClick("Bitter") })
            FlavorCategoryButton("Herbal", onClick = { onClick("Herbal") })
            FlavorCategoryButton("Fruity", onClick = { onClick("Fruity") })
            FlavorCategoryButton("Smoky", onClick = { onClick("Smoky") })
            FlavorCategoryButton("Umami", onClick = { onClick("Umami") })
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
