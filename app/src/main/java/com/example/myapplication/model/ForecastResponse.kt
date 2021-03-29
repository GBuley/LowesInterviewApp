package com.example.myapplication.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastData>,
    val message: Int
)