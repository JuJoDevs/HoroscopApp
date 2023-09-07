package com.jujodevs.horoscapp.data.network

import com.jujodevs.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiService {

    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign:String): PredictionResponse


}