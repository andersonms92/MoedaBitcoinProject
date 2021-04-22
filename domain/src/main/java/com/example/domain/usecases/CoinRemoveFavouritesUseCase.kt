package com.example.domain.usecases

import com.example.domain.repository.CoinRepository

class CoinRemoveFavouritesUseCase(private val repository: CoinRepository) {

    fun execute(id: String) {
        repository.coinRemoveFavourites(id)
    }

}