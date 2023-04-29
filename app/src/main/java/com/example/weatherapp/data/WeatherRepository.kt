package com.example.weatherapp.data

import androidx.lifecycle.LiveData

class WeatherRepository(private val weatherDao: WeatherDao) {

    val readAllData: LiveData<ForecastResponse?> = weatherDao.readAllData()

    suspend fun addCity(forecastResponse: ForecastResponse?) {
        weatherDao.addCity(forecastResponse)
    }


}