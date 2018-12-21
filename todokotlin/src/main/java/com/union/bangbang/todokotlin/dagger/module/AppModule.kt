package com.union.bangbang.todokotlin.dagger.module

import dagger.Module

@Module
class AppModule{

//    //提供 Retrofit 实例
//    @Provides @Singleton
//    fun provideRemoteClient(): Retrofit = Retrofit.Builder()
//            .baseUrl(Constants.HOST_API)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    //提供 PaoService 实例
//    @Provides @Singleton
//    fun providePaoService(client:Retrofit) =client.create(PaoService::class.java)
//
//    //提供 数据库 实例
//    @Provides @Singleton
//    fun provideAppDataBase(application: Application):AppDatabase = AppDatabase.getInstance(application)
//
//    //提供PaoDao 实例
//    @Provides @Singleton
//    fun providePaoDao(dataBase:AppDatabase)=dataBase.paoDao()
}