@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.aslansari.spiritvisor.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aslansari.spiritvisor.cocktail.component.CreditText
import com.aslansari.spiritvisor.theme.icon.HeartSharp
import kotlinx.coroutines.delay

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
        var showLove by remember { mutableStateOf(false) }
        HeartAnimation(showLove = showLove) { showLove = false }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Pick a flavor for your cocktail ...",
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.size(32.dp))
            val buttonPadding = if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) {
                32.dp
            } else {
                12.dp
            }
            FlowRow(
                modifier = Modifier.padding(16.dp).then(
                    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) {
                        Modifier.fillMaxWidth(.5f)
                    } else {
                        Modifier.fillMaxWidth()
                    }
                ),
                horizontalArrangement = Arrangement.spacedBy(buttonPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                maxItemsInEachRow = 3,
            ) {
                uiState.flavors.forEach { flavor ->
                    FlavorCategoryButton(flavor, onClick = { onClick(flavor) })
                }
            }
        }
        CreditText(
            modifier = Modifier.align(Alignment.BottomCenter),
            onLoveSurge = { showLove = true }
        )
    }
}

@Composable
private fun RowScope.FlavorCategoryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier.height(60.dp).requiredWidthIn(max = 250.dp).weight(1f),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
internal fun BoxScope.HeartAnimation(
    modifier: Modifier = Modifier,
    showLove: Boolean = false,
    onFinished: () -> Unit = {},
) {
    val anim by rememberUpdatedState(showLove)
    LaunchedEffect(anim) {
        delay(1000)
        onFinished()
    }
    val heartIconSize by animateDpAsState(targetValue = if (anim) 400.dp else 32.dp, animationSpec = tween(800))
    val animateAlpha by animateFloatAsState(targetValue = if (anim) 1f else 0f, animationSpec = tween(600))
    if (anim) {
        Icon(
            modifier = modifier.zIndex(1f)
                .align(Alignment.Center)
                .alpha(animateAlpha)
                .size(heartIconSize),
            imageVector = Icons.HeartSharp,
            contentDescription = null,
            tint = Color(0xFFDA1E28)
        )
    }
}