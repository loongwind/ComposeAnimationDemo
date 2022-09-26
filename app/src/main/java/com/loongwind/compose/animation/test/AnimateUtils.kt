package com.loongwind.compose.animation.test

import android.animation.ValueAnimator
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp

/**
 * @param state 动画作用的目标 State
 * @param values 动画的变化值，可变参数
 */
fun  animatorOfInt(state: MutableState<Int>, vararg values: Int) : ValueAnimator {
    // 创建 ValueAnimator ，参数为传入的 values
    val animator = ValueAnimator.ofInt(*values)
    // 添加监听
    animator.addUpdateListener {
        // 更新 state 的 value 值
        state.value = it.animatedValue as Int
    }
    return animator
}


@Composable
fun rememberAnimatorOfInt(state: MutableState<Int>, vararg values: Int) : ValueAnimator {
    return remember { animatorOfInt(state, *values) }
}


fun  animatorOfFloat(state: MutableState<Float>, vararg values: Float) : ValueAnimator {
    // 创建 ValueAnimator ，参数为传入的 values
    val animator = ValueAnimator.ofFloat(*values)
    // 添加监听
    animator.addUpdateListener {
        // 更新 state 的 value 值
        state.value = it.animatedValue as Float
    }
    return animator
}


@Composable
fun rememberAnimatorOfFloat(state: MutableState<Float>, vararg values: Float) : ValueAnimator {
    return remember { animatorOfFloat(state, *values) }
}



fun  animatorOfColor(state: MutableState<Color>,  values: Array<Color>) : ValueAnimator {
    val colors:IntArray = values.map { it.toArgb() }.toIntArray()
    // 创建 ValueAnimator ，参数为传入的 values
    val animator = ValueAnimator.ofArgb(*colors)
    // 添加监听
    animator.addUpdateListener {
        // 更新 state 的 value 值
        state.value = Color(it.animatedValue as Int)
    }
    return animator
}


@Composable
fun rememberAnimatorOfColor(state: MutableState<Color>, values: Array<Color>) : ValueAnimator {
    return remember { animatorOfColor(state, values) }
}

fun  animatorOfDp(state: MutableState<Dp>,  values: Array<Dp>) : ValueAnimator {
    val dps:FloatArray = values.map { it.value }.toFloatArray()
    // 创建 ValueAnimator ，参数为传入的 values
    val animator = ValueAnimator.ofFloat(*dps)
    // 添加监听
    animator.addUpdateListener {
        // 更新 state 的 value 值
        state.value = Dp(it.animatedValue as Float)
    }
    return animator
}


@Composable
fun rememberAnimatorOfDp(state: MutableState<Dp>, values: Array<Dp>) : ValueAnimator {
    return remember { animatorOfDp(state, values) }
}


fun mutableIntStateOf(
    value: Int,
    policy: SnapshotMutationPolicy<Int> = structuralEqualityPolicy()
): IntState {
    val state = mutableStateOf(value, policy)
    return IntState(state)

}

class IntState(private val state: MutableState<Int>) {
    var value: Int = state.value
        get() = state.value
        set(value) {
            field = value
            state.value = value
        }
}