package com.example.ymq.quotes

import com.example.ymq.model.Quote
import com.example.ymq.remote.ApiEndpoints
import com.example.ymq.remote.KtorSingletons
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class QuotesApiServiceImpl(
    private val client: HttpClient = KtorSingletons.httpClient
) : QuotesApiService {

    override suspend fun getQuotes(): QuotesResponse? {

        return try {
            val r = client.get<QuotesResponse>(ApiEndpoints.ENDPOINT_QUOTES)
            r
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error: ${ex.response.status.description}")
            null
        }
//        val list = client.get<QuotesResponse>(ApiEndpoints.ENDPOINT_API_NINJA) {
//            method = HttpMethod.Get
//            parameter("X-Api-Key", ApiKeys.KEY_API_NINJA)
//        }
//        // https://ktor.io/docs/client.html#response
//        return list
    }
}