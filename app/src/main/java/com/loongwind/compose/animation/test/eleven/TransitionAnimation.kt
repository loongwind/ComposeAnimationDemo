package com.loongwind.compose.animation.test.eleven

import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loongwind.compose.animation.test.common.ArcShape
import com.loongwind.compose.animation.test.common.VerticalDottedLine

@Preview
@Composable
fun TransitionBaseUse() {
    // 创建状态 通过状态驱动动画
    var moveToRight by remember { mutableStateOf(false) }
    // 动画实例
    val transition = updateTransition(targetState = moveToRight, label = "moveAnimation")

    val paddingStart by transition.animateDp(label = "paddingStart") { state ->
        if (state) {
            200.dp
        } else {
            10.dp
        }
    }
    val corner by transition.animateDp(label = "corner") { state ->
        if (state) {
            20.dp
        } else {
            0.dp
        }
    }
    val color by transition.animateColor(label = "color") { state ->
        if (state) {
            Color.Green
        } else {
            Color.Blue
        }
    }

    Box {
        Box(
            Modifier
                .padding(start = 10.dp)
                .size(1.dp, 160.dp)
        ) {
            VerticalDottedLine()
        }
        Box(
            Modifier
                .padding(start = 200.dp)
                .size(1.dp, 160.dp)
        ) {
            VerticalDottedLine()
        }
        Text(
            text = "10dp",
            Modifier.padding(start = 0.dp, top = 160.dp),
            style = TextStyle(fontSize = 8.sp)
        )
        Text(
            text = "200dp",
            Modifier.padding(start = 190.dp, top = 160.dp),
            style = TextStyle(fontSize = 8.sp)
        )
        Box(
            Modifier
                // 使用动画值
                .padding(start = paddingStart, top = 30.dp)
                .clip(RoundedCornerShape(corner))
                .size(100.dp, 100.dp)
                .background(color)
                .clickable {
                    // 修改状态
                    moveToRight = !moveToRight
                }
        )
    }
}

@Preview
@Composable
fun TransitionLabel() {
    // 创建状态 通过状态驱动动画
    var moveToRight by remember { mutableStateOf(false) }
    // 动画实例
    val transition = updateTransition(targetState = moveToRight, label = "Move")

    val paddingStart by transition.animateDp(label = "paddingStart") { state ->
        if (state) {
            200.dp
        } else {
            10.dp
        }
    }
    val corner by transition.animateDp(label = "corner") { state ->
        if (state) {
            20.dp
        } else {
            0.dp
        }
    }

    Box {
        Box(
            Modifier
                .padding(start = 10.dp)
                .size(1.dp, 160.dp)
        ) {
            VerticalDottedLine()
        }
        Box(
            Modifier
                .padding(start = 200.dp)
                .size(1.dp, 160.dp)
        ) {
            VerticalDottedLine()
        }
        Text(
            text = "10dp",
            Modifier.padding(start = 0.dp, top = 160.dp),
            style = TextStyle(fontSize = 8.sp)
        )
        Text(
            text = "200dp",
            Modifier.padding(start = 190.dp, top = 160.dp),
            style = TextStyle(fontSize = 8.sp)
        )
        Box(
            Modifier
                // 使用动画值
                .padding(start = paddingStart, top = 30.dp)
                .clip(RoundedCornerShape(corner))
                .size(100.dp, 100.dp)
                .background(Color.Blue)
                .clickable {
                    // 修改状态
                    moveToRight = !moveToRight
                }
        )
    }
}

@Preview
@Composable
fun TransitionSpec() {
// 创建状态 通过状态驱动动画
    var moveToRight by remember { mutableStateOf(false) }
    // 动画实例
    val transition = updateTransition(targetState = moveToRight, label = "Move")

    val paddingStart by transition.animateDp(
        transitionSpec = {
//            if(false == initialState && targetState == true){
//                spring()
//            }else{
//                tween()
//            }
            if (false isTransitioningTo true) {
                spring()
            } else {
                tween()
            }
        },
        label = "paddingStart"
    ) { state ->
        if (state) {
            200.dp
        } else {
            10.dp
        }
    }
    val corner by transition.animateDp(
        transitionSpec = { if (false isTransitioningTo true) spring() else tween() },
        label = "corner"
    ) { state ->
        if (state) {
            20.dp
        } else {
            0.dp
        }
    }

    Box {
        Box(
            Modifier
                .padding(start = 10.dp)
                .size(1.dp, 160.dp)
        ) {
            VerticalDottedLine()
        }
        Box(
            Modifier
                .padding(start = 200.dp)
                .size(1.dp, 160.dp)
        ) {
            VerticalDottedLine()
        }
        Text(
            text = "10dp",
            Modifier.padding(start = 0.dp, top = 160.dp),
            style = TextStyle(fontSize = 8.sp)
        )
        Text(
            text = "200dp",
            Modifier.padding(start = 190.dp, top = 160.dp),
            style = TextStyle(fontSize = 8.sp)
        )
        Box(
            Modifier
                // 使用动画值
                .padding(start = paddingStart, top = 30.dp)
                .clip(RoundedCornerShape(corner))
                .size(100.dp, 100.dp)
                .background(Color.Blue)
                .clickable {
                    // 修改状态
                    moveToRight = !moveToRight
                }
        )
    }
}

@Preview
@Composable
fun InfiniteTransition() {
    // 动画实例
    val transition = rememberInfiniteTransition("infiniteMove")
    val animationSpec = infiniteRepeatable<Dp>(
        tween(),
        repeatMode = RepeatMode.Reverse,
        initialStartOffset = StartOffset(300, StartOffsetType.Delay)
    )
    val paddingStart by transition.animateValue(
        initialValue = 10.dp,
        targetValue = 200.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = animationSpec
    )
    Box {
        Box(
            Modifier
                .padding(start = 10.dp)
                .size(1.dp, 160.dp)
        ) {
            VerticalDottedLine()
        }
        Box(
            Modifier
                .padding(start = 200.dp)
                .size(1.dp, 160.dp)
        ) {
            VerticalDottedLine()
        }
        Text(
            text = "10dp",
            Modifier.padding(start = 0.dp, top = 160.dp),
            style = TextStyle(fontSize = 8.sp)
        )
        Text(
            text = "200dp",
            Modifier.padding(start = 190.dp, top = 160.dp),
            style = TextStyle(fontSize = 8.sp)
        )
        Box(
            Modifier
                // 使用动画值
                .padding(start = paddingStart, top = 30.dp)
                .size(100.dp, 100.dp)
                .background(Color.Blue)
        )
    }
}


@Preview
@Composable
fun TransitionInitState() {
// 创建状态 通过状态驱动动画
    var moveToRight by remember { mutableStateOf(true) }
    // 动画实例
    val mutableTransitionState = remember { MutableTransitionState(initialState = !moveToRight) }
    mutableTransitionState.targetState = moveToRight
    val transition = updateTransition(mutableTransitionState, label = "Move")
    val paddingStart by transition.animateDp(label = "Padding") {
        if (it) 200.dp else 10.dp
    }
    Box {
        Box(
            Modifier
                .padding(start = 10.dp)
                .size(1.dp, 160.dp)
        ) {
            VerticalDottedLine()
        }
        Box(
            Modifier
                .padding(start = 200.dp)
                .size(1.dp, 160.dp)
        ) {
            VerticalDottedLine()
        }
        Text(
            text = "10dp",
            Modifier.padding(start = 0.dp, top = 160.dp),
            style = TextStyle(fontSize = 8.sp)
        )
        Text(
            text = "200dp",
            Modifier.padding(start = 190.dp, top = 160.dp),
            style = TextStyle(fontSize = 8.sp)
        )
        Box(
            Modifier
                // 使用动画值
                .padding(start = paddingStart, top = 30.dp)
                .size(100.dp, 100.dp)
                .background(Color.Blue)
                .clickable {
                    // 修改状态
                    moveToRight = !moveToRight
                }
        )
    }
}


enum class UploadState {
    Normal,
    Start,
    Uploading,
    Success
}

data class UploadValue(
    val textAlpha: Float,
    val boxWidth: Dp,
    val progress: Int,
    val progressAlpha: Float,
    val backgroundColor: Color,
    val text: String
)

@Composable
private fun updateTransitionUpload(uploadState: UploadState): UploadValue {
    val transition = updateTransition(targetState = uploadState, label = "upload")
    val textAlpha by transition.animateFloat(label = "textAlpha") {
        when (it) {
            UploadState.Start, UploadState.Uploading -> 0f
            else -> 1f
        }
    }
    val boxWidth by transition.animateDp(label = "boxWidth") {
        when (it) {
            UploadState.Start, UploadState.Uploading -> 48.dp
            else -> 180.dp
        }
    }
    val progress by transition.animateInt(transitionSpec = {
        when {
            UploadState.Start isTransitioningTo UploadState.Uploading -> tween(durationMillis = 1000)
            else -> spring()
        }
    }, label = "progress") {
        when (it) {
            UploadState.Uploading, UploadState.Success -> 100
            else -> 0
        }
    }
    val progressAlpha by transition.animateFloat(label = "progressAlpha") {
        when (it) {
            UploadState.Start, UploadState.Uploading -> 1f
            else -> 0f
        }
    }
    val backgroundColor by transition.animateColor(label = "backgroundColor") {
        when (it) {
            UploadState.Normal -> Color.Blue
            UploadState.Start, UploadState.Uploading -> Color.Gray
            else -> Color.Red
        }
    }
    val text = when (uploadState) {
        UploadState.Success -> "Success"
        else -> "Upload"
    }
    return UploadValue(textAlpha, boxWidth, progress, progressAlpha, backgroundColor, text)

}

@Preview
@Composable
fun UploadDemo() {
    val originWidth = 180.dp
    val circleSize = 48.dp
    var uploadState by remember { mutableStateOf(UploadState.Normal) }
    val uploadValue = updateTransitionUpload(uploadState = uploadState)

    Box(
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .width(originWidth),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(circleSize / 2))
                .background(uploadValue.backgroundColor)
                // 替换为使用 uploadValue.boxWidth
                .size(uploadValue.boxWidth, circleSize)
                .clickable {
                    uploadState = when (uploadState) {
                        UploadState.Normal -> UploadState.Start
                        UploadState.Start -> UploadState.Uploading
                        UploadState.Uploading -> UploadState.Success
                        UploadState.Success -> UploadState.Normal
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Box(
                // 替换为使用 uploadValue.progress
                modifier = Modifier
                    .size(circleSize)
                    .clip(ArcShape(uploadValue.progress))
                    // 替换为使用 uploadValue.progressAlpha
                    .alpha(uploadValue.progressAlpha)
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    // 替换为使用 uploadValue.progressAlpha
                    .alpha(uploadValue.progressAlpha)
                    .background(Color.White)
            )
            // 替换为使用 uploadValue.text、uploadValue.textAlpha
            Text(
                uploadValue.text,
                color = Color.White,
                modifier = Modifier.alpha(uploadValue.textAlpha)
            )
        }
    }
}