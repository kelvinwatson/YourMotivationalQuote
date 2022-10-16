package com.example.ymq.quotes

import kotlinx.coroutines.flow.Flow

interface QuotesRepository {

    val quotesStream: Flow<QuotesResponse>
}