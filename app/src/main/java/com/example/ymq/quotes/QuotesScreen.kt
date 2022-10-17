package com.example.ymq.quotes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.ymq.model.Quote
import com.example.ymq.pagestate.QuotesScreenState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun QuotesRoute(
    navController: NavController,
    viewModel: QuotesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = QuotesViewModel.Factory(resources = LocalContext.current.resources)
    )
) {
    val pageState by viewModel.pageState.collectAsStateWithLifecycle()

    QuotesScreen(
        navController = navController,
        quotesPageState = pageState
    )
}

@Composable
fun QuotesScreen(navController: NavController, quotesPageState: QuotesScreenState) {

    when (quotesPageState) {
        is QuotesScreenState.Loading -> CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        is QuotesScreenState.Ready -> {

            Column {

                Text(text = quotesPageState.imageUrl)
                Text(text = quotesPageState.quote.text!!)
                Text(text = quotesPageState.quote.author!!)
            }
//            LazyVerticalGrid(
//                columns = GridCells.Adaptive(minSize = 128.dp)
//            ) {
//                items<Quote>(quotesPageState.quote) { quote ->
//                    Column {
//                        quote.text?.run {
//                            Text(this)
//                        }
//                        quote.author?.run {
//                            Text(this)
//                        }
//                    }
//                }
//            }
        }
    }

}