package com.example.weatherapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<ForecastResponse?>
    private val repository: WeatherRepository

    fun getCities() : LiveData<ForecastResponse?> {
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