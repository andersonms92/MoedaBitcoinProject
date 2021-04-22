package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.model.CoinFavouriteModel

@Dao
interface CoinFavouriteDao{

    @Query("SELECT * FROM favourites_table")
    fun getFavourites(): List<CoinFavouriteModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavourite(coinFavouriteModel: CoinFavouriteModel)

    @Query("DELETE FROM favourites_table WHERE asset_id = :assetId")
    fun removeFavourite(assetId: String)
}