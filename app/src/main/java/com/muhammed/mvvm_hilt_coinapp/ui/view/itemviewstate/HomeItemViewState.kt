package com.muhammed.mvvm_hilt_coinapp.ui.view.itemviewstate

import com.muhammed.mvvm_hilt_coinapp.data.model.HomeModel
import java.text.NumberFormat

data class HomeItemViewState(val homeModel: HomeModel) {

    fun getSymbol() = homeModel.symbol.uppercase()

    fun getName() = homeModel.name

    fun getImageUrl() = homeModel.image

    fun getCurrentPrice() = "$ " + homeModel.current_price.toString()


    fun getPriceChange24h() = "% " + homeModel.price_change_percentage_24h_in_currency
}
