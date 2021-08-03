package com.muhammed.mvvm_hilt_coinapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeModel(
    val id: String? = "",
    val current_price: Double?,
    val image: String? = "",
    val name: String? = "",
    val symbol: String = "",
    val price_change_percentage_24h_in_currency: Double?
) : Parcelable
