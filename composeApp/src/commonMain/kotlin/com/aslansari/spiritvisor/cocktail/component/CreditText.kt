package com.aslansari.spiritvisor.cocktail.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.aslansari.spiritvisor.theme.icon.HeartSharp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map

@Composable
fun CreditText(
    modifier: Modifier = Modifier,
    onLoveSurge: () -> Unit = {},
) {
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
        val uriHandler = LocalUriHandler.current
        Text(
            modifier = Modifier.clickable(
                onClick = { uriHandler.openUri("https://bento.me/aslansari") },
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ),
            text = creditText,
            style = MaterialTheme.typography.body2.copy(color = Color(0xFF697077))
        )
        Spacer(Modifier.size(2.dp))
        var anim by remember { mutableStateOf(false) }
        LaunchedEffect(anim) {
            delay(200)
            anim = false
        }
        val heartIconSize by animateDpAsState(targetValue = if (anim) 48.dp else 16.dp)
        val heardIconOffset by animateDpAsState(targetValue = if (anim) 0.dp.unaryMinus() else 0.dp)

        val lotsOfLoveCounter = remember { mutableStateOf(0) }
        LaunchedEffect(Unit) {
            snapshotFlow { lotsOfLoveCounter.value }
                .map {
                    if (it > 3) { onLoveSurge() }
                    it
                }
                .debounce(1000)
                .collect {
                    lotsOfLoveCounter.value = 0
                }
        }

        Icon(
            modifier = Modifier
                .offset(y = heardIconOffset)
                .size(heartIconSize)
                .clickable(
                    onClick = {
                        anim = true
                        lotsOfLoveCounter.value++
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ),
            imageVector = Icons.HeartSharp,
            contentDescription = null,
            tint = Color(0xFFDA1E28)
        )
    }
}