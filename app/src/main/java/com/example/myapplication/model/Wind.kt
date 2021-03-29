package com.example.myapplication.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Wind(
    val deg: Int,
    val speed: Double
):Parcelable