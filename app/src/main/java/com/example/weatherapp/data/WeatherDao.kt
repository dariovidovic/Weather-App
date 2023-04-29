package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCity(forecastResponse: ForecastResponse)

    @Query("SELECT * FROM weather_table ORDER BY forecastId ASC")
    fun readAllData() : LiveData<ForecastResponse?>

}