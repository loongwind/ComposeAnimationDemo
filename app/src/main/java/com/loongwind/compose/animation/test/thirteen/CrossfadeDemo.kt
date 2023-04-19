package com.loongwind.compose.animation.test.thirteen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CrossfadeBaseDemo() {
    // 创建状态 控制控件是否显示
    var shown by remember { mutableStateOf(true) }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, start = 100.dp)
    ) {

        Crossfade(shown, animationSpec = tween(1000)) {
            if (it) {
                Box(
                    Modifier
                        // 使用动画值
                        .size(100.dp, 100.dp)
                        .background(Color.Blue)
                )
            } else {
                Box(
                    Modifier
                        .padding(start = 100.dp)
                        .padding(all = 10.dp)
                        // 使用动画值
                        .size(80.dp, 80.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.Red)
                )
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
fun CrossfadeTransitionDemo() {
    // 创建状态 控制控件是否显示
    var shown by remember { mutableStateOf(true) }
    val transition = updateTransition(targetState = shown, label = "Crossfade")

    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, start = 100.dp)
    ) {
        transition.AnimatedVisibility({ it }) {

        }
        transition.Crossfade(animationSpec = tween(1000)) {
            if (it) {
                Box(
                    Modifier
                        // 使用动画值
                        .size(100.dp, 100.dp)
                        .background(Color.Blue)
                )
            } else {
                Box(
                    Modifier
                        .padding(all = 10.dp)
                        // 使用动画值
                        .size(80.dp, 80.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.Red)
                )
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
fun CrossfadeDemo() {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(
        Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation() {
                BottomNavigationItem(
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 },
                    icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "首页") },
                    label = { Text(text = "首页", fontSize = 10.sp) })
                BottomNavigationItem(
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                    icon = { Icon(imageVector = Icons.Filled.Menu, contentDescription = "分类") },
                    label = { Text(text = "分类", fontSize = 10.sp) })
                BottomNavigationItem(
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 },
                    icon = { Icon(imageVector = Icons.Filled.Send, contentDescription = "广场") },
                    label = { Text(text = "广场", fontSize = 10.sp) })
                BottomNavigationItem(
                    selected = selectedIndex == 3,
                    onClick = { selectedIndex = 3 },
                    icon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "我的") },
                    label = { Text(text = "我的", fontSize = 10.sp) })
            }
        }
    ) { innerPadding ->
        println(innerPadding)
        Crossfade(targetState = selectedIndex, animationSpec = tween(1000)) {
            when (it) {
                0 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        Modifier
                            .size(100.dp)
                            .background(Color.Red))
                }
                1 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        Modifier
                            .padding(start = 100.dp, top = 100.dp)
                            .size(100.dp)
                            .background(Color.Blue)
                    )
                }
                2 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        Modifier
                            .padding(start = 200.dp, top = 200.dp)
                            .size(100.dp)
                            .background(Color.Yellow)
                    )
                }
                else -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        Modifier
                            .padding(top = 300.dp)
                            .size(100.dp)
                            .background(Color.Cyan))
                }
            }
        }
    }


}

