package com.example.weatherapp.ui.FutureForecast

import com.example.weatherapp.R
import com.example.weatherapp.db.unitlocalized.future.SpeceficFutureWeather
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_future_weather.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class FutureWeatherItem(
        val weatherEntry: SpeceficFutureWeather
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
        tvStatus.text = weatherEntry.humidity.toString()
            updateDate()
            updateTemp()
        }
    }
    override fun getLayout() = R.layout.item_future_weather
    private fun ViewHolder.updateDate() {
        val dtFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        tvDate.text = weatherEntry.dt.format(dtFormatter)

    }
    private fun ViewHolder.updateTemp() {
        tvTemp.text = weatherEntry.temp.toString()

    }
    }
