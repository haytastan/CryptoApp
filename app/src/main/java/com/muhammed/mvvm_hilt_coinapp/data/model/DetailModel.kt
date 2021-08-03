package com.muhammed.mvvm_hilt_coinapp.data.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coinTable")
data class DetailModel(
    @PrimaryKey(autoGenerate = true)
    val coinId: Int = 0,
    @ColumnInfo(name = "id")
    val id: String? = "",
    @ColumnInfo(name = "name")
    val name: String? = "",
    @ColumnInfo(name = "symbol")
    val symbol: String? = "",
    val image: Image?,
    val market_data: MarketData?
)
data class Image(
    @ColumnInfo(name = "large")
    val large: String
)

data class MarketData(
    @ColumnInfo(name = "current_price")
    val current_price: CurrentPrice?,
    @ColumnInfo(name = "price_change_percentage_24h")
    val price_change_percentage_24h: Double?
)

data class CurrentPrice(
    @SerializedName("usd")
    @ColumnInfo(name = "type")
    val type: Double?
)