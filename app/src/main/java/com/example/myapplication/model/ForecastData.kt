package com.example.myapplication.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ForecastData(
    val clouds: Clouds?,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Float?,
    val rain: Rain?,
    val sys: Sys?,
    val visibility: Int?,
    val weather: List<Weather>,
    val wind: Wind?
):Parcelable{

}