package com.example.ymq

import androidx.compose.material3.NavigationBarItem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ymq.nav.QBottomNavigationBar
import com.example.ymq.nav.QNavHost
import com.example.ymq.nav.Destination
import com.example.ymq.quotes.QuotesViewModel
import com.example.ymq.ui.theme.YourMotivationalQuoteTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YourMotivationalQuoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {

    val screens = listOf(
        Destination.QuotesDestination,
        Destination.SettingsDestination
    )

    val navController = rememberNavController()

    Scaffold(bottomBar = {
        QBottomNavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val onClick = { screen: Destination ->
                navController.navigate(screen.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }
            screens.forEach { screen ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = { onClick(screen) },
                    icon = { Icon(screen.icon, contentDescription = null) },
                    enabled = true,
                    label = { Text(stringResource(id = screen.labelRes)) },
                    alwaysShowLabel = true,
                )
            }
        }
    }) { innerPadding ->

        QNavHost(navController, Modifier.padding(innerPadding))
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewQuotesGrid() {
    YourMotivationalQuoteTheme {
        MainContent()
    }
}