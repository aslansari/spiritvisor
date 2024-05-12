@file:OptIn(ExperimentalLayoutApi::class)

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            Modifier.fillMaxSize(),
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
                FlavorCategoryButton("Bitter", onClick = {})
                FlavorCategoryButton("Herbal", onClick = {})
                FlavorCategoryButton("Fruity", onClick = {})
                FlavorCategoryButton("Smoky", onClick = {})
                FlavorCategoryButton("Umami", onClick = {})
            }
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