package com.example.ymq.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ymq.quotes.QuotesRoute
import com.example.ymq.settings.SettingsRoute

@Composable
fun QNavHost(navController:NavHostController, modifier: Modifier = Modifier,) {
    NavHost(
        navController = navController,
        startDestination = Destination.QuotesDestination.route,
        modifier = modifier,
    ) {
        composable(Destination.QuotesDestination.route) { QuotesRoute(navController) }
        composable(Destination.SettingsDestination.route) { SettingsRoute(navController) }
    }
}