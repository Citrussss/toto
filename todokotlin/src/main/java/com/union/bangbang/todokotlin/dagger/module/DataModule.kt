package com.union.bangbang.todokotlin.dagger.module

import android.app.Application
import android.content.Context
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationListener
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.union.bangbang.todokotlin.BuildConfig
import com.union.bangbang.todokotlin.Constants
import com.union.bangbang.todokotlin.TodoApplication
import com.union.bangbang.todokotlin.base.data.model.DataService
import com.union.bangbang.todokotlin.base.data.net.Api
import com.union.bangbang.todokotlin.base.data.net.Location
import com.union.bangbang.todokotlin.base.okhttp.HeaderInterceptor
import com.union.bangbang.zero.AppUtil
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    //提供 Retrofit 实例
    @Provides
    @Singleton
    fun provideRemoteClient(httpClientBuilder: OkHttpClient.Builder, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.HOST_API)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callFactory(httpClientBuilder.build())
                .build()
    }
    //提供驼峰式转化的gson实体
    @Provides
    @Singleton
    fun provideGson(): Gson {
        var gsonBuilder = GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
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

    @Provides
    @Singleton
    fun okhttpBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .build().newBuilder()
        builder.addInterceptor(HeaderInterceptor())
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor).addInterceptor(StethoInterceptor())
        }
        return builder
    }

    @Provides
    @Singleton
    fun amap(context: Context): Location = Location(context)
}