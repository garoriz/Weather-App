package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.entity

data class Weather(
    val city: String,
    val temp: Double,
    val forecastEntityList: List<ForecastEntity>
)
