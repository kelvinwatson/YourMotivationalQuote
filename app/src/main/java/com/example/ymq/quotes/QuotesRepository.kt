package com.example.ymq.quotes

import com.example.ymq.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {

    val quotesStream: Flow<QuotesResponse?>
}