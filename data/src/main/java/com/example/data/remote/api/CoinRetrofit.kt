package com.example.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoinRetrofit {

    fun getApiInstance() : CoinApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rest.coinapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

       return retrofit.create(CoinApi::class.java)
    }
}