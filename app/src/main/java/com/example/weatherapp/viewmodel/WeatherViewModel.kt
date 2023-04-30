package com.example.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.WeatherDatabase
import com.example.weatherapp.data.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    var readAllData : LiveData<List<ForecastResponse?>>
    private val repository: WeatherRepository

    fun getCities() : LiveData<List<ForecastResponse?>>{
        return readAllData
    }

    init {
        val weatherDao = WeatherDatabase.getDatabase(application).weatherDao()
        repository = WeatherRepository(weatherDao)
        readAllData = repository.readAllData
    }

   fun addCity(forecastResponse: ForecastResponse?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCity(forecastResponse)
        }
    }



}