package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.api.response.weather

data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)
