/*
 * Copyright (c) 2021 Olivier Patry
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.opatry.speedrun.wetrade

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import net.opatry.speedrun.wetrade.ui.home.HomeScreen
import net.opatry.speedrun.wetrade.ui.welcome.LoginScreen
import net.opatry.speedrun.wetrade.ui.welcome.WelcomeScreen

@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MySootheApp()
        }
    }
}

enum class AppState {
    OnBoarding,
    Login,
    Home
}

@ExperimentalMaterialApi
@Composable
fun MySootheApp() {
    ProvideWindowInsets {
        val (appState, setAppState) = remember { mutableStateOf(AppState.OnBoarding) }
        when (appState) {
            AppState.OnBoarding -> WelcomeScreen {
                setAppState(AppState.Login)
            }
            AppState.Login -> LoginScreen {
                setAppState(AppState.Home)
            }
            AppState.Home -> HomeScreen()
        }
        val showGrid = booleanResource(R.bool.debug_show_grid)
        if (showGrid) {
            GridLayer()
        }
    }
}

@Composable
fun GridLayer(gridSize: Dp = 8.dp) {
    Canvas(Modifier.fillMaxSize()) {
        val offset = gridSize.toPx()
        val color = Color.Red.copy(alpha = .3f)
        val lineWidth = 1f
        var x = 0f
        while (x < size.width) {
            drawLine(
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = lineWidth,
                color = color,
            )
            x += offset
        }
        var y = 0f
        while (y < size.height) {
            drawLine(
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = lineWidth,
                color = color,
            )
            y += offset
        }
    }
}
