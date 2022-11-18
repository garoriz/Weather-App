package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data

import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.api.Api
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.api.mapper.WeatherMapper
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.entity.Weather
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: Api,
    private val weatherMapper: WeatherMapper,
) : WeatherRepository {
    override suspend fun getWeatherByLocation(lat: String, lon: String): Weather {
        return weatherMapper.map(api.getWeatherByLocation(lat, lon))
    }

    override suspend fun getWeatherByName(city: String): Weather {
        return weatherMapper.map(api.getWeatherByName(city))
    }
}
