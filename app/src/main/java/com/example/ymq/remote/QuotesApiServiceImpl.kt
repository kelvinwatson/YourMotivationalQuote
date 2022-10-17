package com.example.ymq.remote

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class QuotesApiServiceImpl(
    private val client: HttpClient = KtorSingletons.httpClient
) : QuotesApiService {

    override suspend fun getQuotes(): QuotesResponse {
        return try {
            val response = client.get<QuotesResponse>(ApiEndpoints.ENDPOINT_QUOTES)
            response
        } catch (ex: ResponseException) {
            println("Error: ${ex.response.status.description}")
            QuotesResponse(
                status = ex.response.status.value,
                message = ex.response.status.description,
                count = 0
            )
        }
    }
}