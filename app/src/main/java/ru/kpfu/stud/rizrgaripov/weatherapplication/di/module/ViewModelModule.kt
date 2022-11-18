package ru.kpfu.stud.rizrgaripov.weatherapplication.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.kpfu.stud.rizrgaripov.weatherapplication.di.ViewModelKey
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.presentation.MainViewModel
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.weatherByCityName.presentation.WeatherByCityNameViewModel
import ru.kpfu.stud.rizrgaripov.weatherapplication.utils.AppViewModelFactory

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(
        factory: AppViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(
        viewModel: MainViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherByCityNameViewModel::class)
    fun bindWeatherByCityNameViewModel(
        viewModel: WeatherByCityNameViewModel
    ): ViewModel
}
