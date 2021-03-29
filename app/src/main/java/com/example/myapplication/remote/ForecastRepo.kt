package com.example.myapplication.remote

import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.model.ForecastResponse
import dagger.Provides
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastRepo @Inject constructor(
    private val weatherService: WeatherForecastService
) : ForecastRepository {


    override suspend fun getForecast(cityName: String): ForecastResponse? {
        try {
            return weatherService.getForecast(cityName, BuildConfig.API_KEY, "imperial")
        } catch (ex: HttpException) {
            Log.e("FORECAST_REPO", ex.message())
        } catch (ex: UnknownHostException) {
            Log.e("FORECAST_REPO", ex.message ?: "UnknownHostException caught in ForecastRepo")
        }
        return null
    }
}