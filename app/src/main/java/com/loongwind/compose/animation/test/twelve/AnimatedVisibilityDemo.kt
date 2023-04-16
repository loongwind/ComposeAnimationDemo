package com.loongwind.compose.animation.test.twelve

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random


@Preview
@Composable
fun AnimatedVisibilityColumUse() {
    // 创建状态 控制控件是否显示
    var shown by remember { mutableStateOf(true) }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
            AnimatedVisibility(visible = shown) {
                Box(
                    Modifier
                        // 使用动画值
                        .size(100.dp, 100.dp)
                        .background(Color.Blue)
                )
            }
            Button(onClick = {
                shown = !shown
            }) {
                Text(text = "Switch", style = TextStyle(fontSize = 10.sp))
            }
    }

}

@Preview
@Composable
fun AnimatedVisibilityRowUse() {
    // 创建状态 控制控件是否显示
    var shown by remember { mutableStateOf(true) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 50.dp)
    ) {
        AnimatedVisibility(visible = shown) {
            Box(
                Modifier
                    // 使用动画值
                    .size(100.dp, 100.dp)
                    .background(Color.Blue)
            )
        }
        Button(onClick = {
            shown = !shown
        }, Modifier.padding(start = 10.dp)) {
            Text(text = "Switch", style = TextStyle(fontSize = 10.sp))
        }
    }
}

@Preview
@Composable
fun AnimatedVisibilityBoxUse() {
    // 创建状态 控制控件是否显示
    var shown by remember { mutableStateOf(true) }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 50.dp)
    ){
        AnimatedVisibility(visible = shown) {
            Box(
                Modifier
                    // 使用动画值
                    .size(100.dp, 100.dp)
                    .background(Color.Blue)
            )
        }
        Button(onClick = {
            shown = !shown
        }, Modifier.padding(top = 100.dp)) {
            Text(text = "Switch", style = TextStyle(fontSize = 10.sp))
        }
    }

}



@Preview
@Composable
fun AnimatedVisibilityFadeIn() {
    // 创建状态 控制控件是否显示
    var shown by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 50.dp)
    ){
        AnimatedVisibility(visible = shown, enter = fadeIn(animationSpec = tween(1000), initialAlpha = 0.3f)) {
            Box(
                Modifier
                    // 使用动画值
                    .size(100.dp, 100.dp)
                    .background(Color.Blue)
            )
        }
        Button(onClick = {
            shown = !shown
        }, Modifier.padding(top = 100.dp)) {
            Text(text = "Switch", style = TextStyle(fontSize = 10.sp))
        }
    }

}

@Preview
@Composable
fun AnimatedVisibilitySideIn() {
    // 创建状态 控制控件是否显示
    var shown by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, start = 100.dp)
    ) {
        val enter = slideIn {
            IntOffset(-it.width, -it.height)
        }
        AnimatedVisibility(visible = shown, enter = enter) {
            Box(
                Modifier
                    // 使用动画值
                    .size(100.dp, 100.dp)
                    .background(Color.Blue)
            )
        }
        Button(onClick = {
            shown = !shown
        }, Modifier.padding(top = 100.dp)) {
            Text(text = "Switch", style = TextStyle(fontSize = 10.sp))
        }
    }

}

@Preview
@Composable
fun AnimatedVisibilityExpandIn() {
    // 创建状态 控制控件是否显示
    var shown by remember { mutableStateOf(false) }
    val colors = remember {
        Array(16) { generateRandomColor() }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, start = 100.dp)
    ) {
        val enter = expandIn(
            animationSpec = tween(1000),
            expandFrom = Alignment.BottomEnd,
            clip = false){
            IntSize(it.width/3, it.height/3)
        }
        AnimatedVisibility(visible = shown, enter = enter) {
            Box(
                Modifier
                    // 使用动画值
                    .size(100.dp, 100.dp)
                    .background(Color.Blue)
            ){
                Column() {
                    Row() {
                        Box(Modifier.size(33.33.dp, 33.33.dp).background(colors[0]))
                        Box(Modifier.size(33.33.dp, 33.33.dp).background(colors[1]))
                        Box(Modifier.size(33.33.dp, 33.33.dp).background(colors[2]))
                    }
                    Row() {
                        Box(Modifier.size(33.33.dp, 33.33.dp).background(colors[3]))
                        Box(Modifier.size(33.33.dp, 33.33.dp).background(colors[4]))
                        Box(Modifier.size(33.33.dp, 33.33.dp).background(colors[5]))
                    }
                    Row() {
                        Box(Modifier.size(33.33.dp, 33.33.dp).background(colors[6]))
                        Box(Modifier.size(33.33.dp, 33.33.dp).background(colors[7]))
                        Box(Modifier.size(33.33.dp, 33.33.dp).background(colors[8]))
                    }
                }
            }
        }
        Button(onClick = {
            shown = !shown
        }, Modifier.padding(top = 100.dp)) {
            Text(text = "Switch", style = TextStyle(fontSize = 10.sp))
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun AnimatedVisibilityScaleIn() {
    // 创建状态 控制控件是否显示
    var shown by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, start = 100.dp)
    ) {
        val enter = scaleIn(
            animationSpec = tween(1000),
            initialScale = 0.3f,
            transformOrigin = TransformOrigin(1.0f, 1.0f))
        AnimatedVisibility(visible = shown, enter = fadeIn()+ slideIn(initialOffset = { IntOffset(it.width, it.height) }) + expandIn() + scaleIn()) {
            Box(
                Modifier
                    // 使用动画值
                    .size(100.dp, 100.dp)
                    .background(Color.Blue)
            )
        }
        Button(onClick = {
            shown = !shown
        }, Modifier.padding(top = 100.dp)) {
            Text(text = "Switch", style = TextStyle(fontSize = 10.sp))
        }
    }

}

fun generateRandomColor(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}