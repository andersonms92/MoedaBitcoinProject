package com.example.commons

import android.app.Application
import com.example.commons.di.ApplicationModule
import com.example.data.CoinRepositoryImp
import com.example.data.local.database.CoinFavoriteRoomDatabase
import com.example.data.remote.api.CoinRetrofit
import com.example.domain.di.DomainModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val coinFavoriteRoomDatabase = CoinFavoriteRoomDatabase.getDatabase(this)
        val repository = CoinRepositoryImp(coinFavoriteRoomDatabase, CoinRetrofit.getApiInstance())
        ApplicationModule.domainModule = DomainModule(repository)
    }

}