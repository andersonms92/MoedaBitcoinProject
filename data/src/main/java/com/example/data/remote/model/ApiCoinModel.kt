package com.example.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiCoinModel (
        @SerializedName("asset_id") val assetId : String?,
        val name : String?,
        @SerializedName("volume_1hrs_usd") val valueLastHour: Double?,
        @SerializedName("volume_1day_usd") val valueLastDay: Double?,
        @SerializedName("volume_1mth_usd") val valueLastMonth: Double?,
        @SerializedName("price_usd") val valueUsd: Double?,
        @SerializedName("id_icon") val valueIcon: String?
        )

