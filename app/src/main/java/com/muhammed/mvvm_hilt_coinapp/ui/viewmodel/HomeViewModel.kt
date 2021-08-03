package com.muhammed.mvvm_hilt_coinapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.muhammed.mvvm_hilt_coinapp.data.repository.CoinRepository
import com.muhammed.mvvm_hilt_coinapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinRepository: CoinRepository): ViewModel() {

    fun getCoinList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(coinRepository.getCoinList()))
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: "Failure!"))
        }
    }
}