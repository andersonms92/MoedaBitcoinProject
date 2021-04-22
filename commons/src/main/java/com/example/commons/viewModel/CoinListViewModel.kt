package com.example.commons.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.DomainCoinModel
import com.example.domain.usecases.CoinListUseCase

class CoinListViewModel(
        private val coinListUseCase: CoinListUseCase) : ViewModel() {

    val mutableListOnSuccess = MutableLiveData<List<DomainCoinModel>>()
    val mutableListOnError = MutableLiveData<String>()


    fun listAll() {
        coinListUseCase.execute({
            mutableListOnSuccess.postValue(it)
        }, {
            mutableListOnError.postValue("Error")
        })
    }
}