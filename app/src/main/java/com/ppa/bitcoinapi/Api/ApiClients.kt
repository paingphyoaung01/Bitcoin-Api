package com.ppa.bitcoinapi.Api

import com.ppa.bitcoinapi.model.Article
import com.ppa.bitcoinapi.model.ArticleResult
import com.ppa.bitcoinapi.model.BitcoinResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClients {
    private val apiInterface : ApiInterface

    companion object {
        const val BASE_URL = "https://newsapi.org/"
    }
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface = retrofit.create(ApiInterface::class.java)
    }
    fun getResults(): Call<ArticleResult> {
        return apiInterface.getBitcoin()
    }

}