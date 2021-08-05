package com.muhammed.mvvm_hilt_coinapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.muhammed.mvvm_hilt_coinapp.data.model.DetailModel

@Dao
interface CoinDao {

    @Query("SELECT * FROM coinTable")
    fun getFavoritesCoin(): LiveData<List<DetailModel>>

    @Insert
    suspend fun insertCoin(detailModel: DetailModel)

    @Delete
    suspend fun deleteCoin(detailModel: DetailModel)

}