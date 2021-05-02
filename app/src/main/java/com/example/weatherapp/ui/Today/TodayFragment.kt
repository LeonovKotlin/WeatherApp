package com.example.weatherapp.ui.Today

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders.of
import androidx.lifecycle.ViewModelStores.of
import com.example.weatherapp.R
import com.example.weatherapp.network.APIService
import com.example.weatherapp.network.ConnectInterceptorImpl
import com.example.weatherapp.network.WeatherNetDataSourceImpl
import com.example.weatherapp.ui.base.FragmentScoped
import kotlinx.android.synthetic.main.today_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import retrofit2.Invocation.of
import java.util.EnumSet.of
import java.util.Optional.of

//const val TAG = "MainActivity"

class TodayFragment : FragmentScoped(), KodeinAware {
override val kodein by closestKodein()
private val viewModelFactory: TodayViewModelFactory by instance()

    companion object {
        fun newInstace()=TodayFragment
    }
    private lateinit var viewModel: TodayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this, viewModelFactory).get(TodayViewModel::class.java)
        bindUI()
//        val apiService = APIService(ConnectInterceptorImpl(this.requireContext()))
//        val weatherNetDataSource = WeatherNetDataSourceImpl(apiService)
//        weatherNetDataSource.downloadedCurrentWeather.observe(viewLifecycleOwner, Observer {
//            tv.text = it.toString()
////            tv.text = weatherHistory?.current.toString()
//        })
//        GlobalScope.launch (Dispatchers.Main){
//        weatherNetDataSource.fetchCurrentWeather(53.9,27.56,1619481600)
//        }
//        getHistoricalWeather()
    }

    private fun bindUI()=launch {
        val todayWeather=viewModel.weather.await()
        todayWeather.observe(viewLifecycleOwner, Observer {
            if (it==null) return@Observer
            group_load.visibility = View.GONE
            updateLocation("Minsk")
            updateDate()
            updateTemp(it.temp)
            updatePressure(it.pressure)
            updateSunrise(it.sunrise)
            updateSunset(it.sunset)

        })
    }
    private fun chooseLocUnit(metric: String, imperial: String): String{
        return if (viewModel.isMetric) "mm" else "in"

    }
    private fun updateLocation(loc:String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title=loc
    }
    private fun updateDate() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle="Today"
    }
    private fun updateTemp(temp: Double) {
        val unit = chooseLocUnit("C","F")
        tv_temp.text = "$temp$unit"
//        tv_description.text = "Rain$description$unit"
    }
    private fun updateHudimity(hudimity: Int) {
        tv_humidity.text=hudimity.toString()
    }
   private fun updatePressure(pressure: Int) {
            val unit = chooseLocUnit("mm","in")
            tv_pressure.text="Pressure: $pressure $unit"
    }
    private fun updateSunrise(sunrise: Int) {
        val unit = chooseLocUnit("mm","in")
        tv_sunrise.text="Sunrise: $sunrise $unit"
    }
    private fun updateSunset(sunset: Int) {
        val unit = chooseLocUnit("mm", "in")
        tv_sunset.text = "Sunset: $sunset $unit"
    }
//observ Activ
//    private fun getHistoricalWeather() {
//        lifecycleScope.launchWhenCreated {
//            val response = try {
//                APIService.apiService.getLastWeekWeather()
//            } catch (e: IOException) {
//                Log.e(TAG, "you might not have internet connection")
//                return@launchWhenCreated
//            } catch (e: HttpException) {
//                Log.e(TAG, "you might not have internet connection")
//                return@launchWhenCreated
//            }
//            if (response.isSuccessful && response.body() != null) {
//                val historicalWeather = response.body()
//                Log.d(TAG, "WeatherHistory: $historicalWeather")
//            } else {
//                Log.e(TAG, "Request failed. Code: ${response.code()}, Error: ${response.message()}")
//            }
//        }
//

//    }
}