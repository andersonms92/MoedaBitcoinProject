package com.example.data

import com.example.data.local.dao.CoinFavouriteDao
import com.example.data.local.model.CoinFavouriteModel
import com.example.data.remote.api.CoinApi
import com.example.data.remote.model.ApiCoinModel
import com.example.domain.model.DomainCoinModel
import com.example.domain.repository.CoinRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinRepositoryImp(private val coinFavouriteDao: CoinFavouriteDao, private val api: CoinApi) : CoinRepository {
    override fun coinList(onSuccess: (domainCoins: List<DomainCoinModel>) -> Unit, onError: () -> Unit) {
         api.getCoin().enqueue(object : Callback<List<ApiCoinModel>> {
            override fun onResponse(
                call: Call<List<ApiCoinModel>>,
                response: Response<List<ApiCoinModel>>
            ) {
                if (response.isSuccessful) {

                    val responseBody = response.body()

                    if (responseBody != null) {
                        onSuccess.invoke(mapperToCoinModel(responseBody))
                    } else {
                        onError.invoke()
                    }
                } else {
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<List<ApiCoinModel>>, t: Throwable) {
                onError.invoke()
            }
        })
    }

    override fun coinAddFavourites(id: String) {
        coinFavouriteDao.addFavourite(CoinFavouriteModel(0, id))

    }

    override fun coinRemoveFavourites(id: String) {
        coinFavouriteDao.removeFavourite(id)
    }

    override fun listCoinFavourite(
        onSuccess: (domainCoins: List<DomainCoinModel>) -> Unit,
        onError: () -> Unit
    ) {
        api.getCoin().enqueue(object : Callback<List<ApiCoinModel>> {
            override fun onResponse(
                call: Call<List<ApiCoinModel>>,
                response: Response<List<ApiCoinModel>>
            ) {
                if (response.isSuccessful) {

                    val responseBody = response.body()

                    if (responseBody != null) {
                        val listMapper = mapperToCoinModel(responseBody)
                        onSuccess.invoke(listMapper.filter { it.isFavorited })
                    } else {
                        onError.invoke()
                    }
                } else {
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<List<ApiCoinModel>>, t: Throwable) {
                onError.invoke()
            }
        })
    }

    fun isFavorited(id: String?): Boolean {
        return coinFavouriteDao.getFavourites().firstOrNull { it.assetId == id } != null

    }

    fun mapperToCoinModel(list: List<ApiCoinModel>) =
        list.map { DomainCoinModel(
            it.assetId,
            it.name,
            it.valueLastHour,
            it.valueLastDay,
            it.valueLastMonth,
            it.valueUsd,
            it.valueIcon,
            isFavorited(it.assetId)
        ) }


}

