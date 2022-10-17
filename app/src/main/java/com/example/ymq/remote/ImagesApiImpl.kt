package com.example.ymq.remote

/**
 * Grabs a random image url
 */
class ImagesApiImpl : ImagesApi {
    override suspend fun getImageUrl(): String = "https://picsum.photos/300/300.webp"
}