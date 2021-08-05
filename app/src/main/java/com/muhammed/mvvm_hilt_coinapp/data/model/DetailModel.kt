package com.muhammed.mvvm_hilt_coinapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coinTable")
data class DetailModel(
    @PrimaryKey(autoGenerate = true)
    var coinId: Int = 0,
    @ColumnInfo(name = "id")
    val id: String? = "",
    @ColumnInfo(name = "name")
    val name: String? = "",
    @ColumnInfo(name = "symbol")
    val symbol: String? = "",
    @Embedded
    val image: Image?,
    @Embedded
    val market_data: MarketData?
)

@Entity(tableName = "coinTable")
data class Image(
    @ColumnInfo(name = "large")
    val large: String
)

@Entity(tableName = "coinTable")
data class MarketData(
    @Embedded
    val current_price: CurrentPrice?,
    @ColumnInfo(name = "price_change_percentage_24h")
    val price_change_percentage_24h: Double?
)

@Entity(tableName = "coinTable")
data class CurrentPrice(
    @SerializedName("usd")
    @ColumnInfo(name = "type")
    val type: Double?
)