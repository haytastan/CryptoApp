package com.muhammed.mvvm_hilt_coinapp.di

//@Module
//@InstallIn(SingletonComponent::class)
//object CoinDatabaseModule {
//
//    @Provides
//    @Singleton
//    fun coinDatabase(@ApplicationContext context: Context): CoinDatabase =
//        Room.databaseBuilder(context, CoinDatabase::class.java, "coin.db").build()
//
//    @Provides
//    @Singleton
//    fun coinDao(cryptoDatabase: CoinDatabase): CoinDao =
//        cryptoDatabase.coinDao()
//}