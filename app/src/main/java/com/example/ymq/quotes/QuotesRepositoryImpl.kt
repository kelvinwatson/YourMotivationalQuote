package com.example.ymq.quotes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class QuotesRepositoryImpl(private val service: QuotesApiService = QuotesApiServiceImpl()) :
    QuotesRepository {

    override val quotesStream: Flow<QuotesResponse>
        get() = flow {

            val response = service.getQuotes()
            emit(response)
        }.flowOn(Dispatchers.IO)
}