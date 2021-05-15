package com.example.weatherapp.ui.FutureForecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ForecastFragmentBinding
import com.example.weatherapp.db.unitlocalized.future.SpeceficFutureWeather
import com.example.weatherapp.ui.base.FragmentScoped
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.forecast_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ForecastFragment : FragmentScoped(), KodeinAware {
    override val kodein by closestKodein()
        private val viewModelFactory: FutureViewModelFactory by instance()
        private lateinit var viewModel: ForecastViewModel
        lateinit var binding: ForecastFragmentBinding

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = ForecastFragmentBinding.inflate(inflater, container, false)
            return binding.root
        }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ForecastViewModel::class.java)
        bindUI()
    }
    private fun bindUI() = launch(Dispatchers.Main) {
        val futureWeatherEntry = viewModel.weatherEntries.await()
        val weatherLocation = viewModel.weatherLocation.await()

        weatherLocation.observe(viewLifecycleOwner, Observer { location ->
            if (location == null) return@Observer
            updateLocation(location.name)
        })
        futureWeatherEntry.observe(viewLifecycleOwner, Observer { weatherEntries ->
            if(weatherEntries == null) return@Observer
            binding.loading.visibility = View.GONE

            updateDateToFiveDays()
            initRecyclerView(weatherEntries.toFutureWeatherItems())
        })
    }
    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }
    private fun updateDateToFiveDays() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "FiveDays"
    }
    private fun List<SpeceficFutureWeather>.toFutureWeatherItems() : List<FutureWeatherItem> {
        return this.map {
            FutureWeatherItem(it)
        }
    }
    private fun initRecyclerView(items: List<FutureWeatherItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }
           recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ForecastFragment.context)
           adapter = groupAdapter
           }
        groupAdapter.setOnItemClickListener { item, view ->
            Toast.makeText(this@ForecastFragment.context, "Warn", Toast.LENGTH_SHORT).show()
        }
        }
}

