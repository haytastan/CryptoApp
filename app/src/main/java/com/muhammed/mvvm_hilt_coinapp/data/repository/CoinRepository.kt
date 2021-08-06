package com.muhammed.mvvm_hilt_coinapp.data.repository

import androidx.lifecycle.LiveData
import com.muhammed.mvvm_hilt_coinapp.data.local.CoinDao
import com.muhammed.mvvm_hilt_coinapp.data.model.DetailModel
import com.muhammed.mvvm_hilt_coinapp.data.network.CoinApi
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val coinApi: CoinApi,
    private val coinDao: CoinDao
) {

    suspend fun getCoinList() = coinApi.getCoinList()

    suspend fun getCoinDetail(id: String) = coinApi.getCoinDetail(id)

    suspend fun insertCoin(detailModel: DetailModel) {
        coinDao.insertCoin(detailModel)
    }

    suspend fun deleteCoin(detailModel: DetailModel) {
        coinDao.deleteCoin(detailModel)
    }

    fun getFavoritesCoin(): LiveData<List<DetailModel>> =
        coinDao.getFavoritesCoin()

}