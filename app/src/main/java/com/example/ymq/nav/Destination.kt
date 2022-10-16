package com.example.ymq.nav

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ymq.R
import com.example.ymq.nav.Routes.ROUTE_QUOTES
import com.example.ymq.nav.Routes.ROUTE_SETTINGS

sealed class Destination(val route: String, @StringRes val labelRes: Int, val icon: ImageVector) {
    object QuotesDestination : Destination(ROUTE_QUOTES, R.string.quotes, Icons.Filled.List)
//    object QuoteOfTheDay : Screen(ROUTE_QOTD, R.string.quote_of_the_day, Icons.Filled.Star)
    object SettingsDestination : Destination(ROUTE_SETTINGS, R.string.settings, Icons.Filled.Settings)
}

object Routes {
    const val ROUTE_QUOTES = "quotes"
//    const val ROUTE_QOTD = "quoteOfTheDay"
    const val ROUTE_SETTINGS = "settings"
}
