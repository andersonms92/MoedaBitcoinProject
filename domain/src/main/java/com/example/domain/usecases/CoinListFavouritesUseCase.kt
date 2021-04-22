package com.example.domain.usecases

import com.example.domain.model.DomainCoinModel
import com.example.domain.repository.CoinRepository

class CoinListFavouritesUseCase (private val repository: CoinRepository) {

    fun execute(
        onSuccess: (domainCoins: List<DomainCoinModel>) -> Unit,
        onError: () -> Unit
    ) {
        repository.listCoinFavourite(onSuccess, onError)
    }

}