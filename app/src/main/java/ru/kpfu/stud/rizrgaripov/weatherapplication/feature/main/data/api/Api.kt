package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.api.response.weather.WeatherResponse

interface Api {
    @GET("forecast?")
    suspend fun getWeatherByLocation(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): WeatherResponse

    @GET("forecast?")
    suspend fun getWeatherByName(
        @Query("q") city: String,
    ): WeatherResponse
}
