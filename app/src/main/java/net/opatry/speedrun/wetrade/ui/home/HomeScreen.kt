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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.opatry.speedrun.wetrade.R
import net.opatry.speedrun.wetrade.data.myCategories
import net.opatry.speedrun.wetrade.data.myPositions
import net.opatry.speedrun.wetrade.model.StockPosition
import net.opatry.speedrun.wetrade.ui.component.WeTradeButton
import net.opatry.speedrun.wetrade.ui.home.component.BalanceProgressGraph
import net.opatry.speedrun.wetrade.ui.home.component.Categories
import net.opatry.speedrun.wetrade.ui.home.component.Positions
import net.opatry.speedrun.wetrade.ui.theme.BloomTheme
import net.opatry.speedrun.wetrade.ui.theme.customColors
import net.opatry.speedrun.wetrade.ui.theme.typography

private enum class HomeTabs(
    @StringRes val titleRes: Int,
) {
    Account(R.string.home_account_tab),
    Watchlist(R.string.home_watchlist_tab),
    Profile(R.string.home_profile_tab),
}

@ExperimentalMaterialApi
@Composable
fun HomeScreen(darkTheme: Boolean = isSystemInDarkTheme()) {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(HomeTabs.Account) }
    val tabs = HomeTabs.values()
    BloomTheme(darkTheme) {
        Scaffold(backgroundColor = MaterialTheme.colors.background) {
            PositionsBottomSheet(myPositions) {
                Column {
                    // FIXME would have expected this as Scaffold topBar but doesn't seem to suit BottomSheetScaffold fullscreen
                    TabRow(
                        selectedTabIndex = selectedTab.ordinal,
                        Modifier.padding(horizontal = 16.dp),
                        backgroundColor = Color.Transparent,
                        indicator = { },
                        divider = { }
                    ) {
                        tabs.forEachIndexed { index, tab ->
                            val isSelected = index == selectedTab.ordinal
                            Tab(
                                selected = isSelected,
                                selectedContentColor = MaterialTheme.colors.onBackground,
                                unselectedContentColor = MaterialTheme.colors.onBackground.copy(
                                    alpha = ContentAlpha.disabled
                                ),
                                onClick = { /*setSelectedTab(HomeTabs.values()[index])*/ }
                            ) {
                                val tabName = stringResource(tab.titleRes)
                                Text(
                                    tabName.toUpperCase(),
                                    Modifier.paddingFromBaseline(top = 64.dp, bottom = 8.dp),
                                    style = MaterialTheme.typography.button
                                )
                            }
                        }
                    }
                    Column(
                        Modifier.padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HomeSummary()
                    }
                    Column(Modifier.padding(top = 16.dp, bottom = 32.dp)) {
                        Categories(myCategories)
                        BalanceProgressGraph(
                            painterResource(R.drawable.home_illos),
                            Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeSummary() {
    Text(
        stringResource(R.string.home_balance),
        Modifier.paddingFromBaseline(top = 32.dp, bottom = 8.dp),
        style = MaterialTheme.typography.subtitle1
    )
    // TODO float $ amount + string format
    Text(
        "$73,589.01",
        Modifier.paddingFromBaseline(top = 48.dp, bottom = 24.dp),
        style = MaterialTheme.typography.h1
    )
    Text(
        // TODO reuse StockPositionUIExt
        "+412.35",
        Modifier.paddingFromBaseline(top = 16.dp, bottom = 32.dp),
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.customColors.custom1
    )
    WeTradeButton(
        onClick = { },
        Modifier.fillMaxWidth()
    ) {
        Text(stringResource(R.string.home_transact_cta))
    }
}

@ExperimentalMaterialApi
@Composable
fun PositionsBottomSheet(
    positions: List<StockPosition>,
    content: @Composable (PaddingValues) -> Unit
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    // FIXME status bar text colors when state is expanded
    // if (MaterialTheme.colors.surface.luminance() < .5f) {
    //     if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
    //         // change status bar color to dark
    //     }
    // }

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(0.dp), // MaterialTheme.shapes.small,
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.home_positions),
                    Modifier.paddingFromBaseline(top = 40.dp, bottom = 24.dp),
                    style = typography.subtitle1
                )
                Positions(positions)
            }
        },
        sheetPeekHeight = 64.dp,
        content = content
    )
}

@ExperimentalMaterialApi
@Preview("Home Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun HomeLightPreview() {
    HomeScreen(darkTheme = false)
}

@ExperimentalMaterialApi
@Preview("Home Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun HomeDarkPreview() {
    HomeScreen(darkTheme = true)
}
