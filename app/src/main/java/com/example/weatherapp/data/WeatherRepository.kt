package com.example.weatherapp.data

import androidx.lifecycle.LiveData

class WeatherRepository(private val weatherDao: WeatherDao) {

    //val readAllData: LiveData<ForecastResponse?> = weatherDao.readAllData()

    suspend fun addCity(location: Location){
        weatherDao.addCity(location)
    }

}