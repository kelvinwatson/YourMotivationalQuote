package com.example.ymq.remote

interface QuotesApiService {

    suspend fun getQuotes(): QuotesResponse
}