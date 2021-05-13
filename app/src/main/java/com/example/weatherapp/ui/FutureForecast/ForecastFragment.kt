package com.example.weatherapp.ui.FutureForecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ForecastFragmentBinding
import com.example.weatherapp.databinding.TodayFragmentBinding
import com.example.weatherapp.ui.base.FragmentScoped
import org.kodein.di.Kodein
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
    }
}
