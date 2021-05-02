package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.db.ForecastDatabase
import com.example.weatherapp.network.*
import com.example.weatherapp.repository.ForecastRepository
import com.example.weatherapp.repository.ForecastRepositoryImpl
import com.example.weatherapp.ui.Today.TodayViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao()}
        bind<ConnectInterceptor>() with singleton { ConnectInterceptorImpl(instance()) }
        bind() from singleton { APIService(instance()) }
        bind<WeatherNetDataSource>() with singleton {WeatherNetDataSourceImpl(instance())}
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(),instance()) }
        bind() from provider {TodayViewModelFactory(instance())  }
    }
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
  }
