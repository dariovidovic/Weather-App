package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WeatherRepository(private val weatherDao: WeatherDao) {

    var readAllData: LiveData<List<ForecastResponse?>> = weatherDao.readAllData()

    suspend fun addCity(forecastResponse: ForecastResponse?) {
        weatherDao.addCity(forecastResponse)
    }

    suspend fun deleteCity(forecastResponse: ForecastResponse?){
        weatherDao.deleteCity(forecastResponse)
    }

    suspend fun deleteAllCities(){
        weatherDao.deleteAllCities()
    }



}