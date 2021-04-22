package com.example.domain.di

import com.example.domain.repository.CoinRepository
import com.example.domain.usecases.CoinAddFavouritesUseCase
import com.example.domain.usecases.CoinListFavouritesUseCase
import com.example.domain.usecases.CoinListUseCase
import com.example.domain.usecases.CoinRemoveFavouritesUseCase

class DomainModule(repository: CoinRepository) : IDomainModel {
    override val coinAddFavouritesUseCase: CoinAddFavouritesUseCase by lazy { CoinAddFavouritesUseCase(repository) }

    override val coinListFavouritesUseCase: CoinListFavouritesUseCase by lazy { CoinListFavouritesUseCase(repository) }

    override val coinListUseCase: CoinListUseCase by lazy { CoinListUseCase(repository) }

    override val coinRemoveFavouritesUseCase: CoinRemoveFavouritesUseCase by lazy { CoinRemoveFavouritesUseCase(repository) }


}