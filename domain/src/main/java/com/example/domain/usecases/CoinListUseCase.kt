package com.example.domain.usecases

import com.example.domain.model.DomainCoinModel
import com.example.domain.repository.CoinRepository

class CoinListUseCase (private val repository: CoinRepository) {

    fun execute(
        onSuccess: (domainCoins: List<DomainCoinModel>) -> Unit,
        onError: () -> Unit
    ) {
        repository.coinList(onSuccess, onError)
    }

}