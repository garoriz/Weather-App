package ru.kpfu.stud.rizrgaripov.weatherapplication

import android.app.Application
import ru.kpfu.stud.rizrgaripov.weatherapplication.di.AppComponent
import ru.kpfu.stud.rizrgaripov.weatherapplication.di.DaggerAppComponent
import ru.kpfu.stud.rizrgaripov.weatherapplication.di.module.AppModule
import ru.kpfu.stud.rizrgaripov.weatherapplication.di.module.NetModule

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule())
            .netModule(NetModule())
            .build()
    }
}
