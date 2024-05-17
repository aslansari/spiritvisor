@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.aslansari.spiritvisor.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aslansari.spiritvisor.cocktail.component.CreditText

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
    if (uiState.loading) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
    val windowSizeClass = calculateWindowSizeClass()
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Pick a flavor for your cocktail ...", style = MaterialTheme.typography.h4)
            Spacer(Modifier.size(32.dp))
            FlowRow(
                modifier = Modifier.padding(16.dp).then(
                    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) {
                        Modifier.fillMaxWidth(.5f)
                    } else {
                        Modifier.fillMaxWidth()
                    }
                ),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                maxItemsInEachRow = 3,
            ) {
                uiState.flavors.forEach { flavor ->
                    FlavorCategoryButton(flavor, onClick = { onClick(flavor) })
                }
            }
        }
        CreditText(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun RowScope.FlavorCategoryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier.weight(1f),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
    ) {
        Text(text, style = MaterialTheme.typography.h6)
    }
}
