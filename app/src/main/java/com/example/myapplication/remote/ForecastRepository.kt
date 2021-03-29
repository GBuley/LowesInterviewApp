package com.example.myapplication.remote

import com.example.myapplication.model.ForecastResponse

interface ForecastRepository {

    suspend fun getForecast(cityName: String): ForecastResponse?
}