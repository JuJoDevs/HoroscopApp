package com.jujodevs.horoscapp.data

import android.util.Log
import com.jujodevs.horoscapp.data.network.HoroscopeApiService
import com.jujodevs.horoscapp.domain.Repository
import com.jujodevs.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService):Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("curso", "Ha ocurrido un error ${it.message}") }

        return null
    }

}