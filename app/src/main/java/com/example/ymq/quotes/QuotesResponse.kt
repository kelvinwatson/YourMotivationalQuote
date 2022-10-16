package com.example.ymq.quotes

import com.example.ymq.model.Quote
import com.example.ymq.remote.Response
import kotlinx.serialization.Serializable

@Serializable
data class QuotesResponse(
    val status: Int,
    val message: String?,
    val count: Int,
    val quotes: List<Quote>
) : Response