package com.example.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites_table")
data class CoinFavouriteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo (name = "asset_id") var assetId: String
    )
