package com.example.ymq.remote

import com.example.ymq.model.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class QuotesRepositoryImpl(
    private val quotesService: QuotesApiService = QuotesApiServiceImpl(),
    private val imagesApi: ImagesApi = ImagesApiImpl()
) : QuotesRepository {

    override val contentStream: Flow<Content> =
        combine(quotesStream, imagesStream) { quotesResponse, imageUrl ->
            Content(quotesResponse, imageUrl)
        }.flowOn(Dispatchers.Default)

    override val quotesStream: Flow<QuotesResponse>
        get() = flow {

            val response = quotesService.getQuotes()
            emit(response)
        }.flowOn(Dispatchers.IO)

    override val imagesStream: Flow<String>
        get() = flow {
            emit(imagesApi.getImageUrl())
        }.flowOn(Dispatchers.IO)
}