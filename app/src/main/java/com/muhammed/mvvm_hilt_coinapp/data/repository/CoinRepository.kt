package com.muhammed.mvvm_hilt_coinapp.data.repository

import com.muhammed.mvvm_hilt_coinapp.data.network.CoinApi
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val coinApi: CoinApi
//    private val coinDao: CoinDao
) {

    suspend fun getCoinList() = coinApi.getCoinList()

    suspend fun getCoinDetail(id: String) = coinApi.getCoinDetail(id)

//    suspend fun insertCoin(detailModel: List<DetailModel>) {
//        coinDao.insertCoin(detailModel)
//    }
//
//    suspend fun deleteCoin(detailModel: List<DetailModel>) {
//        coinDao.deleteCoin(detailModel)
//    }
}