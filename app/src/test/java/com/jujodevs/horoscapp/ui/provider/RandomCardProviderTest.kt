package com.jujodevs.horoscapp.ui.provider

import com.jujodevs.horoscapp.motherobject.HoroscopeMotherObject.anyResponse
import io.kotlintest.shouldBe
import org.junit.Test

class RandomCardProviderTest{

    @Test
    fun `toDomain shold return a correct PredictionModel`(){
        //Given
        val predictionResponse = anyResponse.copy(sign = "libra")
        //When
        val predictionModel = predictionResponse.toDomain()
        //Them
        predictionModel.sign shouldBe  predictionResponse.sign
        predictionModel.horoscope shouldBe predictionResponse.horoscope
    }
}