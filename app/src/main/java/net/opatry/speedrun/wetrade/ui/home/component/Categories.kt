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
package net.opatry.speedrun.wetrade.ui.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Categories(categories: List<String>) {
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)) {
        val itemsCount = categories.size
        itemsIndexed(categories) { index, category ->
            OutlinedButton(
                onClick = { },
                Modifier.height(40.dp),
                shape = RoundedCornerShape(50f),
                border = BorderStroke(
                    ButtonDefaults.OutlinedBorderSize,
                    MaterialTheme.colors.onBackground
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.onBackground
                ),
            ) {
                Category(category, isDisplayed = index == 0)
            }
            if (itemsCount > 1 && index < itemsCount - 1) {
                Spacer(Modifier.width(8.dp))
            }
        }
    }
}

@Composable
private fun Category(category: String, isDisplayed: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(category, style = MaterialTheme.typography.body1)
        if (isDisplayed) {
            Icon(
                Icons.Default.ExpandMore,
                "View more",
                Modifier
                    .padding(start = 8.dp)
                    .size(16.dp)
            )
        }
    }
}
