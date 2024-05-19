@file:OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)

package com.aslansari.spiritvisor.cocktail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import com.aslansari.spiritvisor.cocktail.component.CreditText
import com.aslansari.spiritvisor.home.HeartAnimation
import com.aslansari.spiritvisor.theme.icon.LocalBar

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
        var showLove by remember { mutableStateOf(false) }
        HeartAnimation(showLove = showLove) { showLove = false }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Add content here
            SubcomposeAsyncImage(
                modifier = Modifier.height(256.dp).clip(MaterialTheme.shapes.medium),
                model = uiState.cocktailImageUrl,
                contentDescription = "Cocktail Image",
                contentScale = ContentScale.Fit,
                error = {
                    Surface(
                        modifier = Modifier.requiredSize(256.dp),
                        color = Color(0xFFDDE1E6),
                        contentColor = Color(0xFF4D5358),
                        shape = CircleShape,
                    ) {
                        Box(Modifier, contentAlignment = Alignment.Center) {
                            Icon(
                                modifier = Modifier.fillMaxSize(.7f),
                                imageVector = Icons.LocalBar,
                                contentDescription = "Cocktail icon",
                            )
                        }
                    }
                },
                loading = {
                    Box(Modifier.requiredSize(256.dp)) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center).fillMaxSize(.5f),
                            strokeWidth = 4.dp,
                            color = MaterialTheme.colors.primary,
                            strokeCap = StrokeCap.Round,
                        )
                    }
                },
                success = { painter ->
                    SubcomposeAsyncImageContent()
                }
            )
            Spacer(Modifier.size(24.dp))
            Text(uiState.title, style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.SemiBold))
            Spacer(Modifier.size(12.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = uiState.description,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.size(12.dp))
            FlowRow(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                maxItemsInEachRow = 4,
            ) {
                FilterChip(
                    colors = ChipDefaults.filterChipColors(
                        backgroundColor = Color(0xFFF2F4F8), // Gray 10
                        contentColor = Color(0xFF21272A), // Gray 90
                    ),
                    selected = false,
                    content = {
                        Text(
                            uiState.category,
                            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
                        )
                    },
                    onClick = {},
                    modifier = Modifier.padding(end = 8.dp)
                )
                uiState.ingredients.forEachIndexed { index, ingredient ->
                    Row {
                        FilterChip(
                            colors = ChipDefaults.filterChipColors(
                                backgroundColor = Color(0xFFF2F4F8), // Gray 10
                                contentColor = Color(0xFF21272A), // Gray 90
                            ),
                            selected = false,
                            content = {
                                Text(
                                    ingredient,
                                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
                                )
                            },
                            onClick = {},
                        )
                        if (index < uiState.ingredients.lastIndex) {
                            Spacer(Modifier.size(8.dp))
                        }
                    }
                }
            }
            Spacer(Modifier.size(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = onBackClick,
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    Spacer(Modifier.size(2.dp))
                    Text("Change Flavor")
                }
                if (uiState.showSuggestAnother) {
                    OutlinedButton(
                        onClick = onSuggestAnotherClick,
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
                        Spacer(Modifier.size(2.dp))
                        Text("Suggest Another")
                    }
                }
            }
        }
        CreditText(
            modifier = Modifier.align(Alignment.BottomCenter),
            onLoveSurge = { showLove = true },
        )
    }
}
