package com.aslansari.spiritvisor.theme.icon

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun VectorPreview() {
    Image(Icons.HeartSharp, null)
}

private var _HeartSharp: ImageVector? = null

public val Icons.HeartSharp: ImageVector
    get() {
        if (_HeartSharp != null) {
            return _HeartSharp!!
        }
        _HeartSharp = ImageVector.Builder(
            name = "HeartSharp",
            defaultWidth = 16.dp,
            defaultHeight = 17.dp,
            viewportWidth = 16f,
            viewportHeight = 17f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFDA1E28)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(8.00006f, 14.5f)
                lineTo(7.71881f, 14.3125f)
                curveTo(6.3819f, 13.4197f, 4.6904f, 12.4106f, 3.4376f, 10.9275f)
                curveTo(2.1172f, 9.365f, 1.4838f, 7.7603f, 1.5001f, 6.0213f)
                curveTo(1.5197f, 4.0794f, 3.0769f, 2.5f, 4.9713f, 2.5f)
                curveTo(6.4748f, 2.5f, 7.4744f, 3.375f, 8.0001f, 4.0066f)
                curveTo(8.5257f, 3.375f, 9.5254f, 2.5f, 11.0288f, 2.5f)
                curveTo(12.9232f, 2.5f, 14.4804f, 4.0794f, 14.5001f, 6.0203f)
                curveTo(14.5176f, 7.7603f, 13.8841f, 9.3641f, 12.5626f, 10.9266f)
                curveTo(11.3097f, 12.4106f, 9.6182f, 13.4197f, 8.2813f, 14.3125f)
                lineTo(8.00006f, 14.5f)
                close()
            }
        }.build()
        return _HeartSharp!!
    }
