package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.api.mapper

import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.api.response.weather.Forecast
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.api.response.weather.WeatherResponse
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.entity.ForecastEntity
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.entity.Weather

class WeatherMapper {
    fun map(response: WeatherResponse): Weather {
        val listForecast = mutableListOf<ForecastEntity>()
        for (i in 1 until response.list.size) {
            listForecast.add(
                mapForecast(response.list[i])
            )
        }
        return Weather(
            city = response.city.name,
            temp = response.list[0].main.temp,
            forecastEntityList = listForecast
        )
    }

    fun mapForecast(response: Forecast): ForecastEntity {
        return ForecastEntity(
            date = response.dt_txt,
            temp = response.main.temp,
        )
    }
}
