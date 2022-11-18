package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.presentation.diffutils

import androidx.recyclerview.widget.DiffUtil
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.entity.ForecastEntity

class ForecastDiffItemCallback : DiffUtil.ItemCallback<ForecastEntity>() {
    override fun areItemsTheSame(
        oldItem: ForecastEntity,
        newItem: ForecastEntity
    ): Boolean = oldItem.date == newItem.date

    override fun areContentsTheSame(
        oldItem: ForecastEntity,
        newItem: ForecastEntity
    ): Boolean = oldItem == newItem
}
