package com.example.ymq.pagestate

import com.example.ymq.model.Quote

sealed interface QuotesScreenState {

    object Loading : QuotesScreenState

    data class Error(val message: String?, val throwable: Throwable) : QuotesScreenState

    data class Ready(val quotes: List<Quote>) : QuotesScreenState
}