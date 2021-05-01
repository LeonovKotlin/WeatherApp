package com.example.weatherapp.ui.Today

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.weatherapp.R
import com.example.weatherapp.network.APIService
import com.example.weatherapp.network.ConnectInterceptorImpl
import com.example.weatherapp.network.WeatherNetDataSourceImpl
import kotlinx.android.synthetic.main.today_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
//const val TAG = "MainActivity"

class TodayFragment : Fragment() {

    private lateinit var viewModel: TodayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiService = APIService(ConnectInterceptorImpl(this.requireContext()))
        val weatherNetDataSource = WeatherNetDataSourceImpl(apiService)
        weatherNetDataSource.downloadedCurrentWeather.observe(viewLifecycleOwner, Observer {
            tv.text = it.toString()
//            tv.text = weatherHistory?.current.toString()
        })
        GlobalScope.launch (Dispatchers.Main){
        weatherNetDataSource.fetchCurrentWeather(53.9,27.56,1619481600)
        }
//        getHistoricalWeather()

    }

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