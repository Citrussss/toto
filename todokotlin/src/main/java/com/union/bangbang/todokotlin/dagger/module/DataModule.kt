package com.union.bangbang.todokotlin.dagger.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.union.bangbang.todokotlin.BuildConfig
import com.union.bangbang.todokotlin.Constants
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.data.net.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    //提供 Retrofit 实例
    @Provides
    @Singleton
    fun provideRemoteClient(): Retrofit {

        val httpClientBuilder = OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .build().newBuilder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.
                    addInterceptor(loggingInterceptor).
                    addInterceptor(StethoInterceptor())

        }
        return Retrofit.Builder()
                .baseUrl(Constants.HOST_API)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .callFactory(httpClientBuilder.build())
                .build()
    }


    //提供 PaoService 实例
    @Provides
    @Singleton
    fun providePaoService(client: Retrofit): Api = client.create(Api::class.java)

//    //提供 数据库 实例
//    @Provides @Singleton
//    fun provideAppDataBase(application: Application):AppDatabase = AppDatabase.getInstance(application)

    //提供PaoDao 实例
    @Provides
    @Singleton
    fun dataService(api: Api) = DataService(api)
}