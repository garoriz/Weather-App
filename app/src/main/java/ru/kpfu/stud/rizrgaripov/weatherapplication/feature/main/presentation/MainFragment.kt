package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.stud.rizrgaripov.weatherapplication.MainActivity
import ru.kpfu.stud.rizrgaripov.weatherapplication.R
import ru.kpfu.stud.rizrgaripov.weatherapplication.databinding.FragmentMainBinding
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.presentation.adapter.ForecastListAdapter
import ru.kpfu.stud.rizrgaripov.weatherapplication.utils.AppViewModelFactory
import javax.inject.Inject

private const val ARG_CITY_NAME = "city_name"

class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var binding: FragmentMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: MutableLiveData<Result<String>> = MutableLiveData()
    private var lon: MutableLiveData<Result<String>> = MutableLiveData()
    private val viewModel: MainViewModel by viewModels {
        factory
    }
    private var forecastListAdapter: ForecastListAdapter? = null

    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                getLastLocation()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                getLastLocation()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as MainActivity).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        initObservers()

        requestLocationAccess()
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        lon.observe(viewLifecycleOwner) {
            foldLongitude(it)
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

    private fun foldLongitude(lon: Result<String>) {
        lon.fold(onSuccess = {
            foldLatitude(it)
        }, onFailure = {
            showMessage(R.string.not_find_city)
        })
    }

    private fun foldLatitude(lon: String) {
        lat.value?.fold(onSuccess = {
            viewModel.onGetWeather(it, lon)
        }, onFailure = {
            showMessage(R.string.not_find_city)
        })
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
                    showMessage(R.string.not_find_city)
                }
            }
        }
    }

    private fun getWeatherDetailByName(city: String) {
        view?.findNavController()?.navigate(
            R.id.action_mainFragment_to_weatherByCityNameFragment,
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

    private fun requestLocationAccess() {
        locationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun getLastLocation() {
        if (checkPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location == null) {
                        showMessage(R.string.error_getting_location)
                    } else {
                        lat.value = Result.success(location.latitude.toString())
                        lon.value = Result.success(location.longitude.toString())
                    }
                }
        }
    }

    private fun checkPermission(vararg perm: String): Boolean {
        val havePermissions = perm.toList().all {
            ContextCompat.checkSelfPermission(requireContext(), it) ==
                    PackageManager.PERMISSION_GRANTED
        }
        if (!havePermissions) {
            return false
        }
        return true
    }
}
