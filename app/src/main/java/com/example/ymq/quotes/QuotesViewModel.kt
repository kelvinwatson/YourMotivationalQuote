package com.example.ymq.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ymq.pagestate.QuotesScreenState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class QuotesViewModel(
    private val quotesRepository: QuotesRepository = QuotesRepositoryImpl()
) : ViewModel() {

    val pageState: StateFlow<QuotesScreenState> =
        quotesRepository.quotesStream.map { quotesResponse ->
            if (quotesResponse.status == 200 && !quotesResponse.quotes.isNullOrEmpty()) {
                QuotesScreenState.Ready(quotesResponse.quotes)
            } else {
                QuotesScreenState.Error(quotesResponse.message)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = QuotesScreenState.Loading
        )
}