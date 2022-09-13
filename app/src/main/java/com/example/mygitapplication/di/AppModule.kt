package com.example.mygitapplication.di

import android.app.Application
import android.content.Context
import com.example.mygitapplication.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val CUSTOM_CONNECT_TIMEOUT = 40L // seconds

const val CUSTOM_READ_TIMEOUT = 40L
const val GIT_BASE_URL = ""

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application?): Context? {
        return application
    }

    @Provides
    @Singleton
    fun provideGson(): Gson? {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache? {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache?): OkHttpClient? {
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(CUSTOM_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(CUSTOM_READ_TIMEOUT, TimeUnit.SECONDS)
        client.cache(cache)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logging)
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson?, okHttpClient: OkHttpClient?): Retrofit? {
        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            ) //.baseUrl(BASE_URL)
            .baseUrl(GIT_BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
    }
}