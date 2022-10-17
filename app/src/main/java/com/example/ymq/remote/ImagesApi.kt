package com.example.ymq.remote

interface ImagesApi {

    suspend fun getImageUrl(): String
}