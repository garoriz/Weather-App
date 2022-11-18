package ru.kpfu.stud.rizrgaripov.weatherapplication.di.module

import dagger.Binds
import dagger.Module
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.data.WeatherRepositoryImpl
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.repository.WeatherRepository

@Module
interface RepoModule {
    @Binds
    fun weatherRepository(
        impl: WeatherRepositoryImpl
    ): WeatherRepository
}
