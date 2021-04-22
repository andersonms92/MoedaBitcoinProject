package com.example.moedabitcoin.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.commons.di.ApplicationModule
import com.example.commons.viewModel.CoinListViewModel

class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CoinListViewModel::class.java -> {
                with(ApplicationModule) {
                    CoinListViewModel(
                            domainModule.coinListUseCase
                    ) as T
                }
            }
            else -> {
                com.example.favourites.CoinFavouriteViewModel::class.java
                with(ApplicationModule) {
                    com.example.favourites.CoinFavouriteViewModel(
                        domainModule.coinAddFavouritesUseCase,
                        domainModule.coinListFavouritesUseCase,
                        domainModule.coinRemoveFavouritesUseCase
                    ) as T
                }
            }
        }
    }
}
