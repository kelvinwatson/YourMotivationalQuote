package com.example.ymq.model

import kotlinx.serialization.Serializable

@Serializable
data class Quote(val text: String?, val author: String?, val tag: String?)