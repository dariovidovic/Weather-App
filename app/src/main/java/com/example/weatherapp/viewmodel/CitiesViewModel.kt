package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.SearchResponse
import com.example.weatherapp.retrofit.RetrofitHelper
import com.example.weatherapp.retrofit.WeatherService
import com.example.weatherapp.utils.AppConstant.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesViewModel : ViewModel() {

    private var listOfCities = MutableLiveData<List<SearchResponse>?>()
    private var forecastData = MutableLiveData<ForecastResponse?>()


    fun getCities(): MutableLiveData<List<SearchResponse>?> {
        return listOfCities
    }

    fun getForecast(): LiveData<ForecastResponse?> {
        return forecastData
    }


    fun makeApiCall(q: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val retroInstance = RetrofitHelper.getRetroInstance().create(WeatherService::class.java)
            val listOfCitiesResponse =
                retroInstance.getCitiesByName(API_KEY, q).body()
            listOfCities.postValue(listOfCitiesResponse)
        }


    }

    fun makeForecastApiCall(q: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetrofitHelper.getRetroInstance().create(WeatherService::class.java)

            val forecastResponse =
                retroInstance.getForecastByCity(API_KEY, q, "7").body()
            forecastData.postValue(forecastResponse)
        }
    }

}