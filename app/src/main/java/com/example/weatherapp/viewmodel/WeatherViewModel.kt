package com.example.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.WeatherDatabase
import com.example.weatherapp.data.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    var readAllData: LiveData<List<ForecastResponse?>>
    var favCities: LiveData<List<ForecastResponse?>>
    private val repository: WeatherRepository

    private val _editStatus = MutableLiveData(false)
    val editStatus: LiveData<Boolean> = _editStatus


    fun changeEditStatus() {
        _editStatus.value?.let {
            _editStatus.value = !it
        }
    }

    init {
        val weatherDao = WeatherDatabase.getDatabase(application).weatherDao()
        repository = WeatherRepository(weatherDao)
        readAllData = repository.readAllData
        favCities = repository.favCities
    }

    fun addCity(forecastResponse: ForecastResponse?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCity(forecastResponse)
        }
    }

    fun deleteCity(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCity(id)
        }
    }

    fun deleteAllCities() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCities()
        }
    }

    fun setFavStatus(id: Int, isFavouriteStatus: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavStatus(id, isFavouriteStatus)
        }
    }


}