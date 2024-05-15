package com.aslansari.spiritvisor.cocktail.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.aslansari.spiritvisor.theme.icon.HeartSharp

@Composable
fun CreditText(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val creditText = buildAnnotatedString {
            append("created by ")
            withStyle(style = SpanStyle(color = Color(0xFF21272A))) {
                append("Aslan")
            }
            append(" with")
        }
        Text(creditText, style = MaterialTheme.typography.caption.copy(color = Color(0xFF697077)))
        Spacer(Modifier.size(2.dp))
        Icon(
            imageVector = Icons.HeartSharp,
            contentDescription = null,
            tint = Color(0xFFDA1E28)
        )
    }
}