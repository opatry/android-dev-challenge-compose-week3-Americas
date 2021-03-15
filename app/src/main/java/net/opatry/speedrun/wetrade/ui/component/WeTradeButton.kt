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
package net.opatry.speedrun.wetrade.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WeTradeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSecondary: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    if (!isSecondary) {
        Button(
            onClick = onClick,
            modifier.height(48.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = ButtonDefaults.elevation(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            content = content
        )
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier.height(48.dp),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(ButtonDefaults.OutlinedBorderSize, MaterialTheme.colors.primary),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
            content = content
        )
    }
}
