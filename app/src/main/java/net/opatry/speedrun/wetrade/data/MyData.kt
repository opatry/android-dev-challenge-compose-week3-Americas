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
package net.opatry.speedrun.wetrade.data

import net.opatry.speedrun.wetrade.R
import net.opatry.speedrun.wetrade.model.StockPosition

val myCategories = listOf("Week", "ETFs", "Stocks", "Funds", "Foo", "Bar")

val myPositions = listOf(
    StockPosition(
        "ALK",
        name = "Alaska Air Group, Inc.",
        amountInDollar = 7_918f,
        progressPercentage = -0.54f,
        graphData = R.drawable.home_alk
    ),
    StockPosition(
        code = "BA",
        name = "Boeing Co.",
        amountInDollar = 1_293f,
        progressPercentage = 4.18f,
        graphData = R.drawable.home_ba
    ),
    StockPosition(
        code = "DAL",
        name = "Delta Airlines Inc.",
        amountInDollar = 893.5f,
        progressPercentage = -0.54f,
        graphData = R.drawable.home_dal
    ),
    StockPosition(
        code = "EXPE",
        name = "Expedia Group Inc.",
        amountInDollar = 12_301f,
        progressPercentage = 2.51f,
        graphData = R.drawable.home_exp
    ),
    StockPosition(
        code = "EADSY",
        name = "Airbus SE",
        amountInDollar = 12_301f,
        progressPercentage = 2.51f,
        graphData = R.drawable.home_eadsy
    ),
    StockPosition(
        code = "JBLU",
        name = "Jetblue Airways Corp.",
        amountInDollar = 8_251f,
        progressPercentage = 1.56f,
        graphData = R.drawable.home_jblu
    ),
    StockPosition(
        code = "MAR",
        name = "Marriott International Inc.",
        amountInDollar = 521f,
        progressPercentage = 2.75f,
        graphData = R.drawable.home_mar
    ),
    StockPosition(
        code = "CCL",
        name = "Carnival Corp",
        amountInDollar = 5_481f,
        progressPercentage = 0.14f,
        graphData = R.drawable.home_ccl
    ),
    StockPosition(
        code = "RCL",
        name = "Royal Caribbean Cruises",
        amountInDollar = 9_184f,
        progressPercentage = 1.69f,
        graphData = R.drawable.home_rcl
    ),
    StockPosition(
        code = "TRVL",
        name = "Travelocity Inc.",
        amountInDollar = 654f,
        progressPercentage = 3.23f,
        graphData = R.drawable.home_trvl
    )
)
