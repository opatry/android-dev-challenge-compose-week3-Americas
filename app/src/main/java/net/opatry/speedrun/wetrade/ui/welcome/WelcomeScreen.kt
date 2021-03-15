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
package net.opatry.speedrun.wetrade.ui.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.opatry.speedrun.wetrade.R
import net.opatry.speedrun.wetrade.ui.component.WeTradeButton
import net.opatry.speedrun.wetrade.ui.theme.BloomTheme

@Composable
fun WelcomeScreen(onSignIn: () -> Unit) {
    BloomTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colors.background) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painterResource(R.drawable.welcome_bg),
                    null,
                    Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Image(
                    painterResource(R.drawable.logo),
                    stringResource(R.string.app_name)
                )
            }
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                ) {
                    WeTradeButton(onClick = { }, Modifier.weight(1f)) {
                        Text(stringResource(R.string.welcome_getstarted))
                    }
                    Spacer(Modifier.width(8.dp))
                    WeTradeButton(onClick = onSignIn, Modifier.weight(1f), isSecondary = true) {
                        Text(stringResource(R.string.welcome_login))
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview("Welcome", widthDp = 360, heightDp = 640)
@Composable
private fun WelcomePreview() {
    WelcomeScreen {}
}
