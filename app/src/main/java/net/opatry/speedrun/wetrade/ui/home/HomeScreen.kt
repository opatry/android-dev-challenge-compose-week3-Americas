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
package net.opatry.speedrun.wetrade.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import net.opatry.speedrun.wetrade.R
import net.opatry.speedrun.wetrade.data.myPlants
import net.opatry.speedrun.wetrade.data.myThemes
import net.opatry.speedrun.wetrade.ui.component.SearchComponent
import net.opatry.speedrun.wetrade.ui.home.component.PlantsPicker
import net.opatry.speedrun.wetrade.ui.home.component.ThemesBrowser
import net.opatry.speedrun.wetrade.ui.theme.BloomTheme

private enum class HomeTabs(
    @StringRes val titleRes: Int,
    val icon: ImageVector
) {
    Home(R.string.nav_home, Icons.Default.Home),
    Favorites(R.string.nav_favorites, Icons.Default.FavoriteBorder),
    Profile(R.string.nav_profile, Icons.Default.AccountCircle),
    Cart(R.string.nav_cart, Icons.Default.ShoppingCart)
}

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(HomeTabs.Home) }
    val tabs = HomeTabs.values()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {},
        bottomBar = {
            BottomNavigation(
                Modifier.navigationBarsHeight(additional = 56.dp),
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 16.dp
            ) {
                tabs.forEach { navItem ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                navItem.icon,
                                null,
                                Modifier.size(24.dp)
                            )
                        },
                        label = { Text(stringResource(navItem.titleRes)) },
                        selected = selectedTab == navItem,
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onPrimary.copy(alpha = ContentAlpha.medium),
                        modifier = Modifier.navigationBarsPadding(),
                        onClick = { /*setSelectedTab(navItem)*/ }
                    )
                }
            }
        }
    ) {
        Column {
            Spacer(Modifier.height(40.dp))
            SearchComponent(Modifier.padding(horizontal = 16.dp))
            ThemesBrowser(
                myThemes,
                Modifier.padding(horizontal = 16.dp)
            )
            PlantsPicker(
                myPlants,
                Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun HomeLightPreview() {
    BloomTheme {
        HomeScreen()
    }
}

@ExperimentalFoundationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun HomeDarkPreview() {
    BloomTheme(darkTheme = true) {
        HomeScreen()
    }
}
