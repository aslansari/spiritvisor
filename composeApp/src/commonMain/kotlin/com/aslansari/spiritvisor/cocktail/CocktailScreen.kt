@file:OptIn(ExperimentalMaterialApi::class)

package com.aslansari.spiritvisor.cocktail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.aslansari.spiritvisor.cocktail.component.CreditText

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
        onSuggestAnotherClick = viewModel::suggestAnother,
        uiState = uiState
    )
}

@Composable
internal fun CocktailScreen(
    uiState: CocktailUIState,
    onBackClick: () -> Unit = {},
    onSuggestAnotherClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Add content here
            AsyncImage(
                modifier = Modifier.size(256.dp),
                model = uiState.cocktailImageUrl,
                contentDescription = "Cocktail Image",
                contentScale = ContentScale.Fit,
            )
            Spacer(Modifier.size(24.dp))
            Text(uiState.title, style = MaterialTheme.typography.h4)
            Spacer(Modifier.size(12.dp))
            FilterChip(
                selected = false,
                content = {
                    Text(uiState.category)
                },
                onClick = {},
            )
            Spacer(Modifier.size(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onBackClick) {
                    Text("Change Flavor")
                }
                OutlinedButton(onClick = onSuggestAnotherClick) {
                    Text("Suggest Another")
                }
            }
        }
        CreditText(modifier = Modifier.align(Alignment.BottomCenter))
    }
}
