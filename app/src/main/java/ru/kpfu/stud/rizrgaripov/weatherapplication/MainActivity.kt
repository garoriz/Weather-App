package ru.kpfu.stud.rizrgaripov.weatherapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import ru.kpfu.stud.rizrgaripov.weatherapplication.App
import ru.kpfu.stud.rizrgaripov.weatherapplication.R
import ru.kpfu.stud.rizrgaripov.weatherapplication.di.AppComponent
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.presentation.MainFragment

class MainActivity : AppCompatActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        appComponent = (application as App).appComponent
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
