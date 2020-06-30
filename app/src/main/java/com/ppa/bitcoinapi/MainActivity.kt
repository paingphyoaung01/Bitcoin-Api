package com.ppa.bitcoinapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ppa.bitcoinapi.Api.ApiClients
import com.ppa.bitcoinapi.Api.ApiInterface
import com.ppa.bitcoinapi.model.Article
import com.ppa.bitcoinapi.model.BitcoinResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            val bitcoinListFragment = BitcoinListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.screen_container,bitcoinListFragment)
                .commit()
        }
    }
}