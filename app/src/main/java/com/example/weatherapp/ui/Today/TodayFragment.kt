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
    }
    private fun bindUI() = launch {
        val todayWeather = viewModel.weather.await()
        val weatherLocation = viewModel.weatherLocation.await()
        weatherLocation.observe(viewLifecycleOwner, Observer { entry->
            if (entry == null) return@Observer
          updateLocation(entry.name)
          updateWindSpeed(entry.wind!!.speed!!)
        })
        todayWeather.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            updateDate()
            updateTemp(it.temp.toInt().toDouble())
            updatePressure(it.pressure)
            updateHudimity(it.humidity)
            updateTempMax(it.tempMax.toInt().toDouble())
            updateTempMin(it.tempMin.toInt().toDouble())
        })
    }
    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
   }
    private fun updateDate() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }
    private fun updateTemp(temp: Double) {
        binding.tvTemp.text = "${"+"}$temp${"  ͦ C"}"
    }
    private fun updateTempMax(tempMax: Double) {
        binding.tvTempMax.text = "${"Max: "}${"+"}$tempMax${"  ͦ C"}"
    }
    private fun updateTempMin(tempMin:  Double) {
        binding.tvTempMin.text = "${"Min: "}${"+"}$tempMin${"  ͦ C"}"
        }
    private fun updateHudimity(hudimity: Int) {
        binding.tvHumidity.text = hudimity.toString()
    }
    private fun updatePressure(pressure: Int) {
        binding.tvPressure.text = "$pressure${"mm Hg"}"
    }
    private fun updateWindSpeed(windspeed: Double) {
        binding.tvWind.text = "$windspeed${"m/sec"}"
    }
    }