package com.example.ymq.pagestate

import com.example.ymq.model.Quote

sealed interface QuotesScreenState {

    object Loading : QuotesScreenState

    data class Error(val message: String?) : QuotesScreenState

    data class Ready(val quote: Quote, val imageUrl: String) : QuotesScreenState
}