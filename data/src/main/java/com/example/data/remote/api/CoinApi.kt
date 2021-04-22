package com.example.data.remote.api
import com.example.data.remote.model.ApiCoinModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {

    @GET("v1/assets")
    fun getCoin(
        @Query("apikey") apiKey: String = "267ED9D8-119E-44DC-B5D5-2AFC2140E717"
    ): Call<List<ApiCoinModel>>

    @GET("v1/assets?/asset_id")
    fun getCoinDetails(
        @Query("apikey") apiKey: String = "267ED9D8-119E-44DC-B5D5-2AFC2140E717",
        @Query("asset_id") assetId : String
    ): Call<ApiCoinModel>

}

