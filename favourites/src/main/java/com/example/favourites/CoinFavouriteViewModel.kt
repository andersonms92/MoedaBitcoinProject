package com.example.favourites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.DomainCoinModel
import com.example.domain.usecases.CoinAddFavouritesUseCase
import com.example.domain.usecases.CoinListFavouritesUseCase
import com.example.domain.usecases.CoinRemoveFavouritesUseCase

class CoinFavouriteViewModel(
      private val coinAddFavouritesUseCase: CoinAddFavouritesUseCase,
      private val coinListFavouritesUseCase: CoinListFavouritesUseCase,
      private val coinRemoveFavouritesUseCase: CoinRemoveFavouritesUseCase
) : ViewModel() {

     val mutableListOnSuccess = MutableLiveData<List<DomainCoinModel>>()
     val mutableListOnError = MutableLiveData<String>()

   fun addFavourite(id: String) {
       coinAddFavouritesUseCase.execute(id)
   }

   fun removeFavourite(id: String) {
       coinRemoveFavouritesUseCase.execute(id)
   }

   fun listFavourite() {
     coinListFavouritesUseCase.execute({
        mutableListOnSuccess.postValue(it)
     }, {
        mutableListOnError.postValue("Error")
     })
   }
}