package com.muhammed.mvvm_hilt_coinapp.data.network

import com.muhammed.mvvm_hilt_coinapp.data.model.DetailModel
import com.muhammed.mvvm_hilt_coinapp.data.model.HomeModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinApi {

    @GET("coins/markets")
    suspend fun getCoinList(
        @Query("vs_currency") vs_currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") per_page: Int = 100,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false,
        @Query("price_change_percentage") price_change_percentage: String = "24h"
    ): List<HomeModel>

    @GET("coins/{id}")
    suspend fun getCoinDetail(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") market_data: Boolean = true,
        @Query("community_data") community_data: Boolean = false,
        @Query("developer_data") developer_data: Boolean = false,
        @Query("sparkline") sparkline: String = "true"
    ): DetailModel
}