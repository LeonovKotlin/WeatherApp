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
            updateDate()
            updateTemp()
            updateImage()
        }
    }
    override fun getLayout() = R.layout.item_future_weather
    private fun ViewHolder.updateDate() {
        val dtFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        tvDate.text = weatherEntry.dtTxt.format(dtFormatter)
    }
    private fun ViewHolder.updateTemp() {
        tvTemp.text = weatherEntry.temp.toString()
    }
    private fun ViewHolder.updateImage() {
        if (weatherEntry.pop <= 0) {
            ivWeather.setImageResource(R.drawable.big_d40)
        } else if (weatherEntry.pop in 0.1..0.7) {
            ivWeather.setImageResource(R.drawable.big_d10)
        } else if (weatherEntry.pop in 0.7..0.9) {
            ivWeather.setImageResource(R.drawable.big_d40)
        } else if (weatherEntry.pop in 0.9..1.0) {
            ivWeather.setImageResource(R.drawable.big_d01)
        } else {
            ivWeather.setImageResource(R.drawable.big_d20)
        }
    }
}
