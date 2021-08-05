package com.muhammed.mvvm_hilt_coinapp.di

import android.content.Context
import androidx.room.Room
import com.muhammed.mvvm_hilt_coinapp.data.local.CoinDao
import com.muhammed.mvvm_hilt_coinapp.data.local.CoinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinDatabaseModule {

    @Provides
    @Singleton
    fun coinDatabase(@ApplicationContext context: Context): CoinDatabase =
        Room.databaseBuilder(context, CoinDatabase::class.java, "coin.db").build()

    @Provides
    @Singleton
    fun coinDao(cryptoDatabase: CoinDatabase): CoinDao =
        cryptoDatabase.coinDao()
}