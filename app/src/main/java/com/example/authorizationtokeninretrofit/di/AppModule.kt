package com.example.authorizationtokeninretrofit.di

import com.example.authorizationtokeninretrofit.data.network.AuthInterceptor
import com.example.authorizationtokeninretrofit.data.service.ApiService
import com.example.authorizationtokeninretrofit.util.Constance
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Provides
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

object AppModule {
    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi.Builder().run {
        add(KotlinJsonAdapterFactory())
        build()
    }


    @Provides
    @Singleton
    fun providerApiService(moshi: Moshi, okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder().run {
            baseUrl(Constance.BASE_URL)
            addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
            build()
        }.create(ApiService::class.java)


    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(
            AuthInterceptor(
                "Bearer",
                "Qc0VB0-vu-Szor6VgydL405FqFAvAmvjtMZnzT7EHyXSaMnd6ZucTmUoi6JRhDSsIc0OnK0ufDlJkVfE0fhkpFda6yC3yoSgPORh43ejBHkwSrHCIUVI_LZZSJe62Iwah6IlCUybqDWyLNIcm-FEdZmxyfOgQDfE--7qeC-TChmnMmpt0AOwv9c4VVd9VF7XxxYo2rzc5mg0PI9P0mHTKUOXjDRg0Dmdm-yNGau_5ecAml1JncBQNvPX4X4u5BfL1H5OU43tSqvw7THsVS4dAgLmaMKIqi3RYlg7jTfwKwt_id1h_YjqMISOckmGs9L0"
            )
        ).addInterceptor(interceptor).build()
    }
}