package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.repository

import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.entity.Weather

interface WeatherRepository {
    suspend fun getWeatherByLocation(lat: String, lon: String): Weather

    suspend fun getWeatherByName(city: String): Weather
}
