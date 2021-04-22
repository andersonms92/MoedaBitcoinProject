package com.example.domain.repository

import com.example.domain.model.DomainCoinModel

interface CoinRepository {

    fun coinList(
        onSuccess: (domainCoins: List<DomainCoinModel>) -> Unit,
        onError: () -> Unit)

    fun coinAddFavourites(
        id: String
    )

    fun coinRemoveFavourites(
        id: String
    )

    fun listCoinFavourite(
        onSuccess: (domainCoins: List<DomainCoinModel>) -> Unit,
        onError: () -> Unit)
}