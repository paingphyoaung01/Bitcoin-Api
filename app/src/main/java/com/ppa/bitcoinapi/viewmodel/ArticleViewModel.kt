package com.ppa.bitcoinapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ppa.bitcoinapi.Api.ApiClients
import com.ppa.bitcoinapi.model.Article
import com.ppa.bitcoinapi.model.ArticleResult
import retrofit2.Call
import retrofit2.Response

class ArticleViewModel : ViewModel() {

    var result: MutableLiveData<ArticleResult> = MutableLiveData()

    var articleLoadError : MutableLiveData<Boolean> = MutableLiveData()

    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getResult() : LiveData<ArticleResult> = result

    fun getError() : LiveData<Boolean> = articleLoadError

    fun getLoading() : LiveData<Boolean> = loading

    private val apiClients : ApiClients = ApiClients()

    fun loadResults(){
        loading.value = true
        val call = apiClients.getResults()

        call.enqueue(object: retrofit2.Callback<ArticleResult>{
            override fun onFailure(call: Call<ArticleResult>, t: Throwable) {
                articleLoadError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<ArticleResult>, response: Response<ArticleResult>) {
                response?.isSuccessful.let {
                    loading.value = false

                    val resultList = ArticleResult(response?.body()?.articles?: emptyList())

                    result.value = resultList
                }
            }

        })
    }
}