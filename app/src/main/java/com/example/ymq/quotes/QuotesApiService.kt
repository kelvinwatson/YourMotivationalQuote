package com.example.ymq.quotes

interface QuotesApiService {

    suspend fun getQuotes(): QuotesResponse?
}