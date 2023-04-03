package com.example.weatherapp.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.SearchResponse
import com.example.weatherapp.retrofit.RetrofitHelper
import com.example.weatherapp.retrofit.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class CitiesViewModel : ViewModel() {

    var listOfCities = MutableLiveData<List<SearchResponse>?>()
    var forecastData = MutableLiveData<ForecastResponse?>()

    fun getCities() : MutableLiveData<List<SearchResponse>?> {
        return listOfCities
    }
    fun getForecast() : LiveData<ForecastResponse?> {
        return forecastData
    }


    fun makeApiCall(q : String) {
        viewModelScope.launch(Dispatchers.IO) {

            val retroInstance = RetrofitHelper.getRetroInstance().create(WeatherService::class.java)

            val listOfCitiesResponse =
                retroInstance.getCitiesByName("e944a0862777428a870190820233103", q).body()
            listOfCities.postValue(listOfCitiesResponse)
        }


    }

    fun makeForecastApiCall(q: String){
        viewModelScope.launch(Dispatchers.IO) {

            val retroInstance = RetrofitHelper.getRetroInstance().create(WeatherService::class.java)

            val forecastResponse =
                retroInstance.getForecastByCity("e944a0862777428a870190820233103", q, "7").body()
            forecastData.postValue(forecastResponse)
        }
    }

}