package com.example.weatherapp.retrofit

import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("search.json")
    suspend fun getCitiesByName(@Query("key") key: String, @Query("q") q: String ) : Response<List<SearchResponse>>

    @GET("forecast.json")
    suspend fun getForecastByCity(@Query("key") key: String, @Query("q") q: String, @Query("days") days: String) : Response<ForecastResponse>
}