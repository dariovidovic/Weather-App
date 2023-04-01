package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.SearchResponse
import com.example.weatherapp.retrofit.RetrofitHelper
import com.example.weatherapp.retrofit.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    var listOfCities = MutableLiveData<List<SearchResponse>>()
    fun getCities() : LiveData<List<SearchResponse>> {
        return listOfCities
    }


    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {

            val retroInstance = RetrofitHelper.getRetroInstance().create(WeatherService::class.java)

            val listOfCitiesResponse =
                retroInstance.getCitiesByName("e944a0862777428a870190820233103", "London").body()
            listOfCities.postValue(listOfCitiesResponse)
        }


    }

}