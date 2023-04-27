package com.example.weatherapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

   // private val readAllData : LiveData<ForecastResponse?>
    private val repository : WeatherRepository

    init {
        val weatherDao = WeatherDatabase.getDatabase(application).weatherDao()
        repository = WeatherRepository(weatherDao)
        //readAllData = repository.readAllData
    }

    fun addCity(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCity(location)
        }
    }

}