package com.example.weatherapp

import android.app.Application
import android.content.Context
import com.example.weatherapp.db.ForecastDatabase
import com.example.weatherapp.network.*
import com.example.weatherapp.network.provider.LocationPrivider
import com.example.weatherapp.network.provider.LocationPrividerImpl
import com.example.weatherapp.repository.ForecastRepository
import com.example.weatherapp.repository.ForecastRepositoryImpl
import com.example.weatherapp.ui.Today.TodayViewModelFactory
import com.google.android.gms.location.LocationServices
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
        bind() from singleton { instance<ForecastDatabase>().weatherLocDao()}
        bind<ConnectInterceptor>() with singleton { ConnectInterceptorImpl(instance()) }
        bind() from singleton { APIService(instance()) }
        bind<WeatherNetDataSource>() with singleton {WeatherNetDataSourceImpl(instance())}
        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
        bind<LocationPrivider>() with singleton { LocationPrividerImpl(instance(),instance())}
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(),instance(),instance(),instance()) }
        bind() from provider {TodayViewModelFactory(instance())  }
    }
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
  }
