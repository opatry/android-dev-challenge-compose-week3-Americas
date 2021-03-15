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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import net.opatry.speedrun.wetrade.model.StockPosition
import net.opatry.speedrun.wetrade.ui.theme.typography

@Composable
fun Positions(positions: List<StockPosition>) {
    LazyColumn(Modifier.padding(horizontal = 16.dp)) {
        items(positions) { position ->
            Divider(Modifier.height(2.dp))
            Position(position)
        }
    }
}

@Composable
private fun Position(stockPosition: StockPosition) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.width(48.dp)) {
            // FIXME string formatting
            Text(
                "$${stockPosition.amountInDollar}",
                Modifier.paddingFromBaseline(top = 24.dp),
                style = typography.body1
            )
            Text(
                stockPosition.formattedProgressPercentage,
                Modifier.paddingFromBaseline(top = 16.dp, bottom = 16.dp),
                style = typography.body1,
                color = stockPosition.progressColor
            )
        }
        Column(
            Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
        ) {
            Text(
                stockPosition.code,
                Modifier.paddingFromBaseline(top = 24.dp),
                style = typography.h3
            )
            Text(
                stockPosition.name,
                Modifier.paddingFromBaseline(top = 16.dp, bottom = 16.dp),
                style = typography.body1
            )
        }
        Image(
            painterResource(stockPosition.graphData),
            null,
            contentScale = ContentScale.FillHeight
        )
    }
}
