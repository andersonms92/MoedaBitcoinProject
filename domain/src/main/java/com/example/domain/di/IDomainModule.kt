package com.example.domain.di

import com.example.domain.usecases.*

interface IDomainModel {
    val coinAddFavouritesUseCase: CoinAddFavouritesUseCase
    val coinListFavouritesUseCase: CoinListFavouritesUseCase
    val coinListUseCase: CoinListUseCase
    val coinRemoveFavouritesUseCase: CoinRemoveFavouritesUseCase

}