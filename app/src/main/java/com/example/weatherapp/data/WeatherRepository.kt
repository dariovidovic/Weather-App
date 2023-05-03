package com.example.weatherapp.data

import androidx.lifecycle.LiveData

class WeatherRepository(private val weatherDao: WeatherDao) {

    var readAllData: LiveData<List<ForecastResponse?>> = weatherDao.readAllData()
    var favCities: LiveData<List<ForecastResponse?>> = weatherDao.getFavourites()
    var recentCities: LiveData<List<ForecastResponse?>> = weatherDao.getRecent()

    suspend fun addCity(forecastResponse: ForecastResponse?) {
        weatherDao.addCity(forecastResponse)
    }

    suspend fun deleteCity(id: Int){
        weatherDao.deleteCity(id)
    }

    suspend fun deleteAllCities(){
        weatherDao.deleteAllCities()
    }

    suspend fun deleteFavCities(){
        weatherDao.deleteFavCities()
    }

    suspend fun deleteRecentCities(){
        weatherDao.deleteRecentCities()
    }

    suspend fun setFavStatus(id : Int, isFavouriteStatus : Boolean){
        weatherDao.setFavStatus(id, isFavouriteStatus)
    }

    //Recent fragment filtriranje
    suspend fun setRecentStatus(id: Int, isRecentStatus : Boolean){
        weatherDao.setRecentStatus(id, isRecentStatus)
    }




}