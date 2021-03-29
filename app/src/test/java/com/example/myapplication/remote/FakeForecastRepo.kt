package com.example.myapplication.remote

import com.example.myapplication.model.City
import com.example.myapplication.model.Coord
import com.example.myapplication.model.ForecastResponse

class FakeForecastRepo : ForecastRepository {

    private val cities = listOf<String>(
        "Garland",
        "Boston",
        "New York",
        "Chicago",
        "Dallas",
        "Austin",
        "Houston",
        "Wylie",
        "Huntsville"
    )

    override suspend fun getForecast(cityName: String): ForecastResponse? {
        if (cityName.isNotBlank() && cities.contains(cityName)) {
            return ForecastResponse(
                City(
                    Coord(0.0, 0.0),
                    "United States",
                    123,
                    cityName,
                    1000,
                    600,
                    2200,
                    10
                ), 1, "String", listOf(), 200
            )
        } else {
            return null
        }
    }

}