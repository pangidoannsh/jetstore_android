package com.pangidoannsh.jetstore.ui.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun rememberMoreHoriz(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "more_horiz",
            defaultWidth = 16.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(7.708f, 22.917f)
                quadToRelative(-1.208f, 0f, -2.062f, -0.855f)
                quadToRelative(-0.854f, -0.854f, -0.854f, -2.062f)
                quadToRelative(0f, -1.208f, 0.854f, -2.062f)
                quadToRelative(0.854f, -0.855f, 2.062f, -0.855f)
                quadToRelative(1.167f, 0f, 2.063f, 0.834f)
                quadToRelative(0.896f, 0.833f, 0.896f, 2.041f)
                quadToRelative(0f, 1.209f, -0.896f, 2.084f)
                reflectiveQuadToRelative(-2.063f, 0.875f)
                close()
                moveToRelative(12.334f, 0f)
                quadToRelative(-1.209f, 0f, -2.084f, -0.855f)
                quadToRelative(-0.875f, -0.854f, -0.875f, -2.062f)
                quadToRelative(0f, -1.208f, 0.855f, -2.062f)
                quadToRelative(0.854f, -0.855f, 2.062f, -0.855f)
                quadToRelative(1.208f, 0f, 2.062f, 0.834f)
                quadToRelative(0.855f, 0.833f, 0.855f, 2.041f)
                quadToRelative(0f, 1.209f, -0.834f, 2.084f)
                quadToRelative(-0.833f, 0.875f, -2.041f, 0.875f)
                close()
                moveToRelative(12.25f, 0f)
                quadToRelative(-1.209f, 0f, -2.084f, -0.855f)
                quadToRelative(-0.875f, -0.854f, -0.875f, -2.062f)
                quadToRelative(0f, -1.208f, 0.875f, -2.062f)
                quadToRelative(0.875f, -0.855f, 2.084f, -0.855f)
                quadToRelative(1.25f, 0f, 2.083f, 0.834f)
                quadToRelative(0.833f, 0.833f, 0.833f, 2.041f)
                quadToRelative(0f, 1.209f, -0.854f, 2.084f)
                reflectiveQuadToRelative(-2.062f, 0.875f)
                close()
            }
        }.build()
    }
}