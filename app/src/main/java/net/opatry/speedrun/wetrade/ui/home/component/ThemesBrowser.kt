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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.opatry.speedrun.wetrade.R
import net.opatry.speedrun.wetrade.data.myThemes
import net.opatry.speedrun.wetrade.model.Theme
import net.opatry.speedrun.wetrade.ui.theme.BloomTheme
import net.opatry.speedrun.wetrade.ui.theme.typography

@Composable
fun ThemesBrowser(themes: List<Theme>, modifier: Modifier = Modifier) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            stringResource(R.string.browse_themes),
            Modifier
                .paddingFromBaseline(top = 32.dp, bottom = 16.dp)
                .then(modifier),
            style = typography.h1
        )
        LazyRow(
            Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 2.dp)
        ) {
            itemsIndexed(themes) { index, theme ->
                if (index != 0) {
                    Spacer(Modifier.width(8.dp))
                }
                ThemeCard(stringResource(theme.name), painterResource(theme.picture))
            }
        }
    }
}

@Composable
fun ThemeCard(name: String, picture: Painter) {
    Card(
        Modifier.size(136.dp),
        shape = MaterialTheme.shapes.small
        // FIXME background color is displayed twice with a padding, why?
    ) {
        Column(Modifier.clickable { }) {
            Image(
                picture,
                null,
                Modifier
                    .fillMaxWidth()
                    .height(96.dp),
                contentScale = ContentScale.FillBounds
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    name,
                    textAlign = TextAlign.Center,
                    style = typography.h2
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun CollectionsLightPreview() {
    BloomTheme {
        ThemesBrowser(myThemes, Modifier.padding(horizontal = 16.dp))
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun CollectionsDarkPreview() {
    BloomTheme(darkTheme = true) {
        ThemesBrowser(myThemes, Modifier.padding(horizontal = 16.dp))
    }
}
