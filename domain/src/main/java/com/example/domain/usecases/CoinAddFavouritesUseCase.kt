package com.example.domain.usecases

import com.example.domain.repository.CoinRepository

class CoinAddFavouritesUseCase(private val repository: CoinRepository) {

    fun execute(id: String) {
        repository.coinAddFavourites(id)
    }


}