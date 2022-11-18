package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.entity.Weather
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.usecase.GetWeatherByLocationUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getWeatherByLocationUseCase: GetWeatherByLocationUseCase
) : ViewModel() {
    private var _weather: MutableLiveData<Result<Weather>> = MutableLiveData()
    val weather: LiveData<Result<Weather>> = _weather

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    fun onGetWeather(lat: String, lon: String) {
        viewModelScope.launch {
            try {
                val weather = getWeatherByLocationUseCase(lat, lon)
                _weather.value = Result.success(weather)
            } catch (ex: Exception) {
                _weather.value = Result.failure(ex)

                _error.value = ex
            }
        }
    }
}
