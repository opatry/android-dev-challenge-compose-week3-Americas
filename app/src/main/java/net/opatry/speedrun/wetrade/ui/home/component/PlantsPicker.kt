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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.opatry.speedrun.wetrade.R
import net.opatry.speedrun.wetrade.data.myPlants
import net.opatry.speedrun.wetrade.model.Plant
import net.opatry.speedrun.wetrade.ui.theme.BloomTheme
import net.opatry.speedrun.wetrade.ui.theme.typography

@Composable
fun PlantsPicker(plants: List<Plant>, modifier: Modifier = Modifier) {
    Column {
        Row(modifier, verticalAlignment = Alignment.Bottom) {
            Text(
                stringResource(R.string.design_your_home_garden),
                Modifier
                    .weight(1f)
                    .paddingFromBaseline(top = 40.dp),
                style = typography.h1
            )
            Icon(
                Icons.Default.FilterList,
                stringResource(R.string.filter_plants),
                Modifier
                    .clickable { }
                    .size(24.dp)
            )
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 0.dp
            )
        ) {
            itemsIndexed(plants) { index, plant ->
                val (isSelected, onSelect) = remember { mutableStateOf(index == 0) }
                PlantDescription(
                    stringResource(plant.name),
                    painterResource(plant.picture),
                    stringResource(plant.description),
                    isSelected = isSelected,
                    onToggle = onSelect
                )
            }
        }
    }
}

@Composable
fun PlantDescription(
    name: String,
    picture: Painter,
    description: String,
    isSelected: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Column {
        Row {
            Image(
                picture,
                null,
                Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.FillBounds
            )
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(
                        Modifier
                            .weight(1f)
                            .padding(start = 16.dp)
                    ) {

                        Text(name, Modifier.paddingFromBaseline(top = 24.dp), style = typography.h2)
                        Text(
                            description,
                            Modifier.paddingFromBaseline(bottom = 24.dp),
                            style = typography.body1
                        )
                    }
                    Checkbox(
                        checked = isSelected,
                        onCheckedChange = onToggle,
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = MaterialTheme.colors.onSecondary,
                            checkedColor = MaterialTheme.colors.secondary
                        )
                    )
                }
                Divider(
                    Modifier
                        .height(1.dp)
                        .padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun ActivitiesLightPreview() {
    BloomTheme {
        PlantsPicker(myPlants, Modifier.padding(horizontal = 16.dp))
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun ActivitiesDarkPreview() {
    BloomTheme(darkTheme = true) {
        PlantsPicker(myPlants, Modifier.padding(horizontal = 16.dp))
    }
}
