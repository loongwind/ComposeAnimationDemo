package com.loongwind.compose.animation.test.common

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ArcShape(private val progress: Int) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val angle = progress / 100f * 360f
        val path = Path().apply {
            moveTo(size.width / 2f, size.height / 2f)
            val rect = Rect(0f, 0f, size.width, size.height)
            if(angle == 360f){
                addOval(rect)
            }else{
                arcTo(rect, -90f, angle, false)
            }

            close()
        }
        return Outline.Generic(path)
    }
}