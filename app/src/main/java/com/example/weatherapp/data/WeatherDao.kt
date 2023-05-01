package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(forecastResponse: ForecastResponse?)

    @Delete
    suspend fun deleteCity(forecastResponse: ForecastResponse?)

    @Query("DELETE FROM weather_table")
    suspend fun deleteAllCities()

    @Query("SELECT * FROM weather_table ORDER BY forecastId ASC")
    fun readAllData(): LiveData<List<ForecastResponse?>>




}