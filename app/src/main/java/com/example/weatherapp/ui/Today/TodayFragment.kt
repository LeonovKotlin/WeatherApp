package com.example.weatherapp.ui.Today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.TodayFragmentBinding
import com.example.weatherapp.ui.base.FragmentScoped
import kotlinx.android.synthetic.main.today_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

//const val TAG = "MainActivity"

class TodayFragment : FragmentScoped(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: TodayViewModelFactory by instance()
    lateinit var binding: TodayFragmentBinding
    private lateinit var viewModel: TodayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TodayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(TodayViewModel::class.java)//
        bindUI()
//        val apiService = APIService(ConnectInterceptorImpl(this.requireContext()))
//        val weatherNetDataSource = WeatherNetDataSourceImpl(apiService)
//        weatherNetDataSource.downloadedCurrentWeather.observe(viewLifecycleOwner, Observer {
//            tv.text = it.toString()
//            tv.text = weatherHistory?.current.toString()
//        })
//        GlobalScope.launch (Dispatchers.Main){
//        weatherNetDataSource.fetchCurrentWeather(53.9,27.56,1619481600)
//        }
//        getHistoricalWeather()
    }
    private fun bindUI() = launch {
        val todayWeather = viewModel.weather.await()
        val weatherLocation = viewModel.weatherLocation.await()
        weatherLocation.observe(viewLifecycleOwner, Observer { coord ->
            if (coord == null) return@Observer              /////
           updateLocation(coord.lat.toString())            /////API
        })
        //name coord
        todayWeather.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
//            tv_all.text = it.toString()
 //           group_load.visibility = View.GONE
            updateDate()
            updateTemp(it.temp)
            updatePressure(it.pressure)
            updateHudimity(it.humidity)
            updateTempMax(it.tempMax)
            updateTempMin(it.tempMin)
//          Glide.with(this@TodayFragment).load("${it.icon}")
//         .into(icon_weather)
        })
    }
    private fun chooseLocUnit(metric: String, imperial: String): String {
        return if (viewModel.isMetric) metric else imperial
    }
    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }
    private fun updateDate() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }
    private fun updateTemp(temp: Double) {
        val unit = chooseLocUnit("C", "F")
        binding.tvTemp.text = "$temp$unit"
    }
    private fun updateTempMax(tempMax: Double) {
        val unit = chooseLocUnit("C", "F")
        binding.tvTempMax.text = "$tempMax$unit"
    }
    private fun updateTempMin(tempMin: Double) {
        val unit = chooseLocUnit("C", "F")
        binding.tvTempMin.text = "$tempMin$unit"
        }
    private fun updateHudimity(hudimity: Int) {
        tv_humidity.text = hudimity.toString()
    }
    private fun updatePressure(pressure: Int) {
        val unit = chooseLocUnit("mm", "in")
        tv_pressure.text = "Pressure: $pressure $unit"
    }
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
//}