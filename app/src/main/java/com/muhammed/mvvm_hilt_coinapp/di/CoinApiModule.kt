package com.muhammed.mvvm_hilt_coinapp.di

import com.muhammed.mvvm_hilt_coinapp.data.network.CoinApi
import com.muhammed.mvvm_hilt_coinapp.util.AppInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinApiModule {

    @Provides
    @Singleton
    fun retrofitClient(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(AppInfo.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun createApi(retrofit: Retrofit): CoinApi =
        retrofit.create(CoinApi::class.java)
}