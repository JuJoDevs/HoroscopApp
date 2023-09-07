package com.jujodevs.horoscapp.domain

import com.jujodevs.horoscapp.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign:String): PredictionModel?
}