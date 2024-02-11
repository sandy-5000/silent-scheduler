package com.darkube.silentScheduler.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

const val Silent = "M8 5v14l11-7z"
fun svgIcon(
    size: Dp = 24.dp,
    viewport: Float = 24f,
): ImageVector {
    return ImageVector.Builder(
        defaultWidth = size,
        defaultHeight = size,
        viewportHeight = viewport,
        viewportWidth = viewport,
    ).run {
        addPath(
            pathData = addPathNodes(Silent),
            fill = SolidColor(Color.White),
        )
        build()
    }
}
