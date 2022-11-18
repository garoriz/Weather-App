package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.weatherByCityName.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.stud.rizrgaripov.weatherapplication.MainActivity
import ru.kpfu.stud.rizrgaripov.weatherapplication.R
import ru.kpfu.stud.rizrgaripov.weatherapplication.databinding.FragmentMainBinding
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.presentation.adapter.ForecastListAdapter
import ru.kpfu.stud.rizrgaripov.weatherapplication.utils.AppViewModelFactory
import javax.inject.Inject

private const val ARG_CITY_NAME = "city_name"

class WeatherByCityNameFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var binding: FragmentMainBinding
    private val viewModel: WeatherByCityNameViewModel by viewModels {
        factory
    }
    private var forecastListAdapter: ForecastListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as MainActivity).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        initObservers()

        val cityName = arguments?.getString(ARG_CITY_NAME)
        if (cityName != null) {
            viewModel.onGetWeather(cityName)
        } else {
            binding.tvCity.text = getString(R.string.not_find_city)
        }

        with(binding) {
            svCity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    getWeatherDetailByName(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
    }

    private fun initObservers() {
        viewModel.weather.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = {
                with(binding) {
                    progressBar.visibility = View.GONE
                    tvC.visibility = View.VISIBLE
                    tvCurrentWeather.text = it.temp.toString()
                    tvCity.text = it.city

                    val forecastList = it.forecastEntityList as MutableList

                    forecastListAdapter = ForecastListAdapter()

                    binding.weathers.run {
                        adapter = forecastListAdapter
                    }

                    forecastListAdapter?.submitList(forecastList)
                }
            }, onFailure = {
                Log.e("e", it.message.toString())
            })
        }

        viewModel.error.observe(viewLifecycleOwner) {
            when (it) {
                is Exception -> {
                    binding.progressBar.visibility = View.GONE
                    showMessage(R.string.not_find_city)
                }
            }
        }
    }

    private fun getWeatherDetailByName(city: String) {
        view?.findNavController()?.navigate(
            R.id.action_weatherByCityNameFragment_self,
            bundleOf(ARG_CITY_NAME to city)
        )
    }


    private fun showMessage(stringId: Int) {
        Snackbar.make(
            binding.root,
            stringId,
            Snackbar.LENGTH_LONG
        ).show()
    }
}
