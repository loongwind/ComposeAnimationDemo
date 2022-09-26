package com.loongwind.compose.animation.test

import android.animation.AnimatorSet
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.animation.addListener

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UploadButton()
        }
    }
}


@Preview
@Composable
fun UploadButton() {
    val originWidth = 180.dp
    val circleSize = 48.dp
    var text by remember { mutableStateOf("Upload") }
    val textAlpha = remember { mutableStateOf(1.0f) }
    val backgroundColor = remember { mutableStateOf(Color.Blue) }
    val boxWidth = remember { mutableStateOf(180.dp) }
    val progressAlpha = remember { mutableStateOf(0.0f) }
    val progress = remember { mutableStateOf(0) }

    val endAnimatorSet = remember {
        val animatorSet = AnimatorSet()
        val widthAnimator = animatorOfDp(boxWidth, arrayOf(circleSize, originWidth))
        val centerAnimator = animatorOfFloat(progressAlpha, 1f, 0f)
        val textAnimator = animatorOfFloat(textAlpha, 0f, 1f)
        val colorAnimator = animatorOfColor(backgroundColor, arrayOf(Color.Blue, Color.Red))
        animatorSet.playTogether(widthAnimator, centerAnimator, textAnimator, colorAnimator)
        animatorSet.addListener(onStart = {
            text = "Success"
        })
        animatorSet
    }


    val progressAnimator = remember {
        val animator = animatorOfInt(progress, 0, 100)
        animator.duration = 1000
        animator.addListener(onEnd = {
            endAnimatorSet.start()
        })
        animator
    }

    val uploadStartAnimator = remember {
        val animatorSet = AnimatorSet()
        val widthAnimator = animatorOfDp(boxWidth, arrayOf(originWidth, circleSize))
        val textAnimator = animatorOfFloat(textAlpha, 1f, 0.0f)
        val colorAnimator = animatorOfColor(backgroundColor, arrayOf(Color.Blue, Color.Gray))
        val centerAlphaAnimator = animatorOfFloat(progressAlpha, 0.0f, 1f)
        animatorSet.playTogether(widthAnimator, textAnimator, colorAnimator, centerAlphaAnimator)
        animatorSet.addListener(onEnd = {
            progressAnimator.start()
        })
        animatorSet
    }

    Box(
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .width(originWidth),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(circleSize/2))
                .background(backgroundColor.value)
                .size(boxWidth.value, circleSize)
                .clickable {
                    uploadStartAnimator.start()
                },
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier.size(circleSize).clip(ArcShape(progress.value))
                    .alpha(progressAlpha.value).background(Color.Blue)
            )
            Box(
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(20.dp))
                    .alpha(progressAlpha.value).background(Color.White)
            )
            Text(text, color = Color.White, modifier = Modifier.alpha(textAlpha.value))
        }
    }
}


class ArcShape(private val progress: Int) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2f, size.height / 2f)
            arcTo(Rect(0f, 0f, size.width, size.height), -89.9f, progress / 100f * 360f, false)
            close()
        }
        return Outline.Generic(path)
    }
}