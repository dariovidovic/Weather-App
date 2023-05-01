package com.example.weatherapp.data

import androidx.lifecycle.LiveData

class WeatherRepository(private val weatherDao: WeatherDao) {

    var readAllData: LiveData<List<ForecastResponse?>> = weatherDao.readAllData()
    var favCities: LiveData<List<ForecastResponse?>> = weatherDao.getFavourites()

    suspend fun addCity(forecastResponse: ForecastResponse?) {
        weatherDao.addCity(forecastResponse)
    }

    suspend fun deleteCity(id: Int){
        weatherDao.deleteCity(id)
    }

    suspend fun deleteAllCities(){
        weatherDao.deleteAllCities()
    }

    suspend fun setFavStatus(id : Int, isFavouriteStatus : Boolean){
        weatherDao.setFavStatus(id, isFavouriteStatus)
    }



}