package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.entity.Weather
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherByNameUseCase@Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(city: String): Weather {
        return withContext(dispatcher) {
            weatherRepository.getWeatherByName(city)
        }
    }
}
