package com.ppa.bitcoinapi.Api

import com.ppa.bitcoinapi.model.Article
import com.ppa.bitcoinapi.model.ArticleResult
import com.ppa.bitcoinapi.model.BitcoinResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    @Headers("X-Api-Key: 8ca0c0243cb7482c915eea8f88cde616")
    @GET("v2/everything?q=bitcoin")
    fun getBitcoin() : Call<ArticleResult>
}