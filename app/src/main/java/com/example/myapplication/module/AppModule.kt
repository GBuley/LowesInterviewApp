package com.example.myapplication.module

import com.example.myapplication.remote.ForecastRepo
import com.example.myapplication.remote.ForecastRepository
import com.example.myapplication.remote.WeatherForecastService
import com.example.myapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }.let { OkHttpClient.Builder().addInterceptor(it).build() }

    @Singleton
    @Provides
    fun provideWeatherForecastService(client: OkHttpClient): WeatherForecastService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(WeatherForecastService::class.java)
    }


    @Singleton
    @Provides
    fun provideForecastRepo(weatherForecastService: WeatherForecastService) = ForecastRepo(weatherForecastService) as ForecastRepository

}