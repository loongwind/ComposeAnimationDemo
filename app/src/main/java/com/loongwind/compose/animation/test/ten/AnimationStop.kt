package com.loongwind.compose.animation.test

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch


@Preview
@Composable
fun AnimationInterrupt() {
    // 创建状态 通过状态驱动动画
    var moveToRight by remember { mutableStateOf(false) }
    var backgroundColor by remember { mutableStateOf(Color.Blue) }
    // 动画实例
    val animatable = remember { Animatable(10.dp, Dp.VectorConverter) }

    // animateTo 是 suspend 方法，所以需要在协程中进行调用
//    LaunchedEffect(moveToRight) {
//        // 根据状态确定动画移动的目标值
//        animatable.animateTo(if (moveToRight) 200.dp else 10.dp, animationSpec = tween(durationMillis = 1000))
//    }
    val scope = rememberCoroutineScope()

    Box {
        Box(
            Modifier
                .padding(start = 10.dp)
                .size(1.dp, 160.dp)
        ){
            VerticalDottedLine()
        }
        Box(
            Modifier
                .padding(start = 200.dp)
                .size(1.dp, 160.dp)
        ){
            VerticalDottedLine()
        }
        Box(
            Modifier
                .padding(start = 50.dp)
                .size(1.dp, 160.dp)
        ){
            VerticalDottedLine()
        }
        Text(text = "10dp", Modifier.padding(start = 0.dp, top = 160.dp), style = TextStyle(fontSize = 8.sp))
        Text(text = "200dp", Modifier.padding(start = 190.dp, top = 160.dp), style = TextStyle(fontSize = 8.sp))
        Text(text = "50dp", Modifier.padding(start = 40.dp, top = 160.dp), style = TextStyle(fontSize = 8.sp))
        Box(
            Modifier
                // 使用动画值
                .padding(start = animatable.value, top = 30.dp)
                .size(100.dp, 100.dp)
                .background(backgroundColor)
                .clickable {
                    // 修改状态
                    moveToRight = !moveToRight
                    backgroundColor = Color.Blue
                    scope.launch {
                        try {
                            animatable.animateTo(
                                if (moveToRight) 200.dp else 10.dp,
                                animationSpec = tween(durationMillis = 1000)
                            )
                        } catch (e: CancellationException) {
                            Log.e("触发", "CancellationException")
                        }
                    }
                }
        )
        Button(onClick = {
            backgroundColor = Color.Cyan
            scope.launch {
                animatable.animateTo(50.dp, animationSpec = tween(durationMillis = 1000))
            }
        }, Modifier.padding(top = 170.dp, start = 70.dp)) {
            Text(text = "Next", style = TextStyle(fontSize = 10.sp))
        }
    }

}

@Preview
@Composable
fun StopAnimation() {
    // 创建状态 通过状态驱动动画
    var moveToRight by remember { mutableStateOf(false) }
    var backgroundColor by remember { mutableStateOf(Color.Blue) }
    // 动画实例
    val animatable = remember { Animatable(10.dp, Dp.VectorConverter) }

    val scope = rememberCoroutineScope()

    Box {
        Box(
            Modifier
                .padding(start = 10.dp)
                .size(1.dp, 160.dp)
        ){
            VerticalDottedLine()
        }
        Box(
            Modifier
                .padding(start = 200.dp)
                .size(1.dp, 160.dp)
        ){
            VerticalDottedLine()
        }

        Text(text = "10dp", Modifier.padding(start = 0.dp, top = 160.dp), style = TextStyle(fontSize = 8.sp))
        Text(text = "200dp", Modifier.padding(start = 190.dp, top = 160.dp), style = TextStyle(fontSize = 8.sp))
        Box(
            Modifier
                // 使用动画值
                .padding(start = animatable.value, top = 30.dp)
                .size(100.dp, 100.dp)
                .background(backgroundColor)
                .clickable {
                    // 修改状态
                    moveToRight = !moveToRight
                    backgroundColor = Color.Blue
                    scope.launch {
                        try {
                            animatable.animateTo(
                                if (moveToRight) 200.dp else 10.dp,
                                animationSpec = tween(durationMillis = 1000)
                            )
                        } catch (e: CancellationException) {
                            Log.e("触发", "CancellationException")
                        }
                    }
                }
        )
        Button(onClick = {
            backgroundColor = Color.Cyan
            scope.launch {
                animatable.stop()
            }
        }, Modifier.padding(top = 170.dp, start = 70.dp)) {
            Text(text = "Stop", style = TextStyle(fontSize = 10.sp))
        }
    }

}

@Composable
private fun VerticalDottedLine() {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Canvas(
        Modifier
            .fillMaxHeight()
            .width(1.dp)) {
        drawLine(
            color = Color.Red,
            start = Offset(0f, 0f),
            end = Offset(0f, size.height),
            pathEffect = pathEffect
        )
    }
}