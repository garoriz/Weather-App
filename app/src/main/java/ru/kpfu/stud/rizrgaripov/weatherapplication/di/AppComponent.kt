package ru.kpfu.stud.rizrgaripov.weatherapplication.di

import dagger.Component
import ru.kpfu.stud.rizrgaripov.weatherapplication.MainActivity
import ru.kpfu.stud.rizrgaripov.weatherapplication.di.module.AppModule
import ru.kpfu.stud.rizrgaripov.weatherapplication.di.module.NetModule
import ru.kpfu.stud.rizrgaripov.weatherapplication.di.module.RepoModule
import ru.kpfu.stud.rizrgaripov.weatherapplication.di.module.ViewModelModule
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.presentation.MainFragment
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.weatherByCityName.presentation.WeatherByCityNameFragment

@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        RepoModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(mainFragment: MainFragment)

    fun inject(weatherByCityNameFragment: WeatherByCityNameFragment)
}
