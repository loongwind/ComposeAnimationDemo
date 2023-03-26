package com.loongwind.compose.animation.test.eleven

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
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
    val transition = updateTransition(targetState = moveToRight)

    val paddingStart by transition.animateDp { state ->
        if (state) {
            200.dp
        } else {
            10.dp
        }
    }
    val corner by transition.animateDp { state ->
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
        transitionSpec = { if (false isTransitioningTo true) spring() else tween() },
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
// 创建状态 通过状态驱动动画
    var moveToRight by remember { mutableStateOf(false) }
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
                .clickable {
                    // 修改状态
                    moveToRight = !moveToRight
                }
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
    val progressAlpha: Float
) {
    companion object
}

val UploadValue.Companion.VectorConverter: TwoWayConverter<UploadValue, AnimationVector4D>
    get() = UploadToVector


private val UploadToVector: TwoWayConverter<UploadValue, AnimationVector4D> = TwoWayConverter(
    convertToVector = {
        AnimationVector4D(
            it.textAlpha,
            it.boxWidth.value,
            it.progress.toFloat(),
            it.progressAlpha
        )
    },
    convertFromVector = { UploadValue(it.v1, Dp(it.v2), it.v3.toInt(), it.v4) }
)

@Preview
@Composable
fun UploadDemo() {
    val originWidth = 180.dp
    val circleSize = 48.dp
    var uploadState by remember { mutableStateOf(UploadState.Normal) }
    var text by remember { mutableStateOf("Upload") }

    val transition = updateTransition(targetState = uploadState, label = "upload")

    val uploadValue by transition.animateValue(transitionSpec = {
        when {
            UploadState.Normal isTransitioningTo UploadState.Start -> spring()
            else -> tween()
        }
    }, label = "uploadValue", typeConverter = UploadValue.VectorConverter) { state ->
        when (state) {
            UploadState.Normal -> UploadValue(1f, originWidth, 0, 0f)
            UploadState.Start -> UploadValue(0f, circleSize, 0, 1f)
            UploadState.Uploading -> UploadValue(0f, circleSize, 100, 1f)
            UploadState.Success -> UploadValue(1f, originWidth, 100, 0f)
        }
    }
    val backgroundColor by transition.animateColor(label = "backgroundColor") { state ->
        when (state) {
            UploadState.Normal -> Color.Blue
            UploadState.Start -> Color.Gray
            UploadState.Uploading -> Color.Gray
            UploadState.Success -> Color.Red
        }
    }


    // 创建 UploadValue 的 State
//    val upload by  animateUploadAsState(uploadValue){
//        // 监听动画完成修改状态
//        if(uploadState == UploadState.Start){
//            uploadState = UploadState.Uploading
//        }else if(uploadState == UploadState.Uploading){
//            uploadState = UploadState.Success
//            text = "Success"
//        }
//    }


    Box(
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .width(originWidth),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(circleSize / 2))
                .background(backgroundColor)
                // 替换为使用 upload.boxWidth
                .size(uploadValue.boxWidth, circleSize)
                .clickable {
                    if (uploadState == UploadState.Normal) {
                        uploadState = UploadState.Start
                    } else if (uploadState == UploadState.Start) {
                        uploadState = UploadState.Uploading
                    } else if (uploadState == UploadState.Uploading) {
                        uploadState = UploadState.Success
                        text = "Success"
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Box(
                // 替换为使用 upload.progress
                modifier = Modifier
                    .size(circleSize)
                    .clip(ArcShape(uploadValue.progress))
                    // 替换为使用 upload.progressAlpha
                    .alpha(uploadValue.progressAlpha)
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    // 替换为使用 upload.progressAlpha
                    .alpha(uploadValue.progressAlpha)
                    .background(Color.White)
            )
            // 替换为使用 upload.textAlpha
            Text(text, color = Color.White, modifier = Modifier.alpha(uploadValue.textAlpha))
        }
    }
}