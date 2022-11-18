package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.api.response.weather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
