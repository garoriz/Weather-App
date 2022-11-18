package ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.domain.entity.ForecastEntity
import ru.kpfu.stud.rizrgaripov.weatherapplication.feature.main.presentation.diffutils.ForecastDiffItemCallback

class ForecastListAdapter : ListAdapter<ForecastEntity, ForecastHolder>(
    ForecastDiffItemCallback()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastHolder = ForecastHolder.create(parent)

    override fun onBindViewHolder(holder: ForecastHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<ForecastEntity>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}
