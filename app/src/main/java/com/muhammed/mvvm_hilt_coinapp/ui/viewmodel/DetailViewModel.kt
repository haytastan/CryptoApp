package com.muhammed.mvvm_hilt_coinapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.muhammed.mvvm_hilt_coinapp.data.model.DetailModel
import com.muhammed.mvvm_hilt_coinapp.data.repository.CoinRepository
import com.muhammed.mvvm_hilt_coinapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val coinRepository: CoinRepository) :
    ViewModel() {


    fun insertCoin(detailModel: DetailModel) {
        viewModelScope.launch {
            coinRepository.insertCoin(detailModel)
        }
    }

    fun deleteCoin(detailModel: DetailModel) {
        viewModelScope.launch {
            coinRepository.deleteCoin(detailModel)
        }
    }

    var id = ""
    fun getCoinDetail() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(coinRepository.getCoinDetail(id)))
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: "Failure!"))
        }
    }
}