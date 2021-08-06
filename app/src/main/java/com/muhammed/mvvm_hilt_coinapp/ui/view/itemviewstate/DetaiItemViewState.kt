package com.muhammed.mvvm_hilt_coinapp.ui.view.itemviewstate

import com.muhammed.mvvm_hilt_coinapp.data.model.DetailModel
import java.text.NumberFormat

data class DetaiItemViewState(
    val detailModel: DetailModel
) {
    fun getName() = detailModel.name

    fun getSymbol() = "(" + detailModel.symbol + ")"

    fun getImageUrl() = detailModel.image?.large

    fun getCurrentPrice() = detailModel.market_data?.current_price?.type.toString()

    fun getPriceChange(): String {
        val nf: NumberFormat = NumberFormat.getInstance()
        nf.maximumFractionDigits = 2
        return nf.format(detailModel.market_data?.price_change_percentage_24h) + "%"
    }

}
