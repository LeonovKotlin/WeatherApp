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
    weatherEntry.temp.toInt().toDouble()
    tvTemp.text = "${"+"}${weatherEntry.temp}${" ͦ C"}"
    }
    private fun ViewHolder.updateImage() {
        if (weatherEntry.pop <= 0) {
            ivWeather.setImageResource(R.drawable.big_d40)
            tvDesc.text = "Overcast Cloud"
        } else if (weatherEntry.pop in 0.1..0.4) {
            ivWeather.setImageResource(R.drawable.big_d30)
            tvDesc.text = "Broken Clouds"
        } else if (weatherEntry.pop in 0.0..0.1 && weatherEntry.pressure in 1005..1006) {
            ivWeather.setImageResource(R.drawable.big_d40)
            tvDesc.text = "Overcast Cloud"
        } else if (weatherEntry.pop in 0.44..0.9 && weatherEntry.pressure in 1002..1009) {
            ivWeather.setImageResource(R.drawable.big_d90)
            tvDesc.text = "light rain"
        } else if (weatherEntry.pop in 0.9..1.0 ) {
            ivWeather.setImageResource(R.drawable.big_d01)
            tvDesc.text = "Moderate rain"
        } else {
            ivWeather.setImageResource(R.drawable.big_d20)
        }
    }
}
