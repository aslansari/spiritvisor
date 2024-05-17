package com.aslansari.spiritvisor.theme.icon

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun VectorPreview() {
    Image(Icons.LocalBar, null)
}

private var _LocalBar: ImageVector? = null

internal val Icons.LocalBar: ImageVector
    get() {
        if (_LocalBar != null) {
            return _LocalBar!!
        }
        _LocalBar = ImageVector.Builder(
            name = "LocalBar",
            defaultWidth = 25.dp,
            defaultHeight = 25.dp,
            viewportWidth = 25f,
            viewportHeight = 25f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF1C1B1F)),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(6.66943f, 21.9361f)
                    verticalLineTo(19.9361f)
                    horizontalLineTo(11.6694f)
                    verticalLineTo(14.9361f)
                    lineTo(3.66943f, 5.9361f)
                    verticalLineTo(3.9361f)
                    horizontalLineTo(21.6694f)
                    verticalLineTo(5.9361f)
                    lineTo(13.6694f, 14.9361f)
                    verticalLineTo(19.9361f)
                    horizontalLineTo(18.6694f)
                    verticalLineTo(21.9361f)
                    horizontalLineTo(6.66943f)
                    close()
                    moveTo(8.11943f, 7.9361f)
                    horizontalLineTo(17.2194f)
                    lineTo(19.0194f, 5.9361f)
                    horizontalLineTo(6.31943f)
                    lineTo(8.11943f, 7.9361f)
                    close()
                    moveTo(12.6694f, 13.0361f)
                    lineTo(15.4444f, 9.9361f)
                    horizontalLineTo(9.89443f)
                    lineTo(12.6694f, 13.0361f)
                    close()
                }
            }
        }.build()
        return _LocalBar!!
    }
