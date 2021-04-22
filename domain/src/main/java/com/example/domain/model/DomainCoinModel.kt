package com.example.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainCoinModel (
        val assetId : String?,
        val name : String?,
        val valueLastHour: Double?,
        val valueLastDay: Double?,
        val valueLastMonth: Double?,
        val valueUsd: Double?,
        val valueIcon: String?,
        var isFavorited: Boolean = false
        ):Parcelable

