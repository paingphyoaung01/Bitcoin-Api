package com.ppa.bitcoinapi.model

data class BitcoinResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)