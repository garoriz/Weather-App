package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.stud.rizrgaripov.weatherapplication.databinding.ItemWeatherBinding
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.entity.ForecastEntity

class ForecastHolder(
    private val binding: ItemWeatherBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private var forecast: ForecastEntity? = null

    fun bind(item: ForecastEntity) {
        this.forecast = item
        with(binding) {
            tvDate.text = item.date
            tvTemp.text = item.temp.toString()
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
        ) = ForecastHolder(
            ItemWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
