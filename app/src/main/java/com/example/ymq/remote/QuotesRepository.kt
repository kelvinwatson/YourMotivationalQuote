package com.example.ymq.remote

import com.example.ymq.model.Content
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {
    val contentStream: Flow<Content>

    val quotesStream: Flow<QuotesResponse>

    val imagesStream: Flow<String>
}