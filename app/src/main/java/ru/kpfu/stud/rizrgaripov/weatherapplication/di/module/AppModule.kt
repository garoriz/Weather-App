package ru.kpfu.stud.rizrgaripov.weatherapplication.di.module

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.api.mapper.WeatherMapper

@Module
class AppModule {
    @Provides
    fun provideWeatherMapper(): WeatherMapper = WeatherMapper()

    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
