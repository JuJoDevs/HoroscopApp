package com.jujodevs.horoscapp.data.network

import com.jujodevs.horoscapp.data.RepositoryImpl
import com.jujodevs.horoscapp.data.core.interceptors.AuthInterceptor
import com.jujodevs.horoscapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://newastro.vercel.app/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor):OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(authInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopeApiService = retrofit.create(HoroscopeApiService::class.java)

    @Singleton
    @Provides
    fun provideRepository(apiService: HoroscopeApiService):Repository = RepositoryImpl(apiService)

}