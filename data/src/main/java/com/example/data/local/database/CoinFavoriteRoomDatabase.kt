package com.example.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.CoinFavouriteDao
import com.example.data.local.model.CoinFavouriteModel

@Database(entities = [CoinFavouriteModel::class], version = 1, exportSchema = false)
abstract class CoinFavoriteRoomDatabase (): RoomDatabase() {

    abstract fun coinFavoriteDao(): CoinFavouriteDao

    companion object {
        @Volatile
        private var INSTANCE: CoinFavoriteRoomDatabase? = null

        fun getDatabase(context: Context): CoinFavouriteDao {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance.coinFavoriteDao()
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CoinFavoriteRoomDatabase::class.java,
                    "favourites_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance.coinFavoriteDao()
            }
        }
    }
}
