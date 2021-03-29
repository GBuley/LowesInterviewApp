package com.example.myapplication.remote

import com.example.myapplication.BuildConfig
import com.example.myapplication.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastService {

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = BuildConfig.API_KEY,
        @Query("units") units: String
    ): ForecastResponse

}