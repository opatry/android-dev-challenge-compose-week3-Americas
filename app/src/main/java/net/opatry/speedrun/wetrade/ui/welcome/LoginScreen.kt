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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.opatry.speedrun.wetrade.R
import net.opatry.speedrun.wetrade.ui.component.WeTradeButton
import net.opatry.speedrun.wetrade.ui.theme.BloomTheme
import net.opatry.speedrun.wetrade.ui.theme.typography

@Composable
fun LoginScreen(darkTheme: Boolean = isSystemInDarkTheme(), onSignedIn: () -> Unit) {
    BloomTheme(darkTheme) {
        Surface(color = MaterialTheme.colors.surface) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painterResource(R.drawable.login_bg),
                        null,
                        Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        stringResource(R.string.login_welcome_back),
                        Modifier
                            .fillMaxWidth(.7f)
                            .paddingFromBaseline(top = 152.dp),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground,
                        style = typography.h2
                    )
                }
                // TODO integrate to column or push column just below image
                Column(
                    Modifier.padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var email by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp)
                            .height(56.dp),
                        leadingIcon = {
                            Icon(Icons.Default.MailOutline, null)
                        },
                        placeholder = {
                            // TODO replicate text style
                            Text(
                                stringResource(R.string.login_email_address),
                                style = typography.body1
                            )
                        }
                    )

                    var password by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        leadingIcon = {
                            Icon(Icons.Default.Password, null)
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        placeholder = {
                            // TODO replicate text style
                            Text(stringResource(R.string.login_password), style = typography.body1)
                        }
                    )
                    WeTradeButton(
                        onClick = onSignedIn,
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(stringResource(R.string.login_login))
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview("Login Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LoginLightPreview() {
    LoginScreen(darkTheme = false) {}
}

@ExperimentalFoundationApi
@Preview("Login Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LoginDarkPreview() {
    LoginScreen(darkTheme = true) {}
}
