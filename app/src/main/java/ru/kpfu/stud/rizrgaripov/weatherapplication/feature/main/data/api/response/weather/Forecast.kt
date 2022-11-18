package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.api.response.weather

data class Forecast(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val snow: Snow,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
