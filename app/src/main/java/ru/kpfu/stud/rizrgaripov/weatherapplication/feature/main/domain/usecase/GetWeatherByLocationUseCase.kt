package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.entity.Weather
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherByLocationUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(lat: String, lon: String): Weather {
        return withContext(dispatcher) {
            weatherRepository.getWeatherByLocation(lat, lon)
        }
    }
}
