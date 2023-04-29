package com.example.weatherapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "weather_table")
data class ForecastResponse(
    @PrimaryKey(autoGenerate = true)
    var forecastId: Int = 0,
    var location: Location,
    var current: Current,
    var forecast: Forecast,
    var forecastday: ForecastDay,
    var day: Day,
    var hour: Hour


) : Serializable

data class Location(
    var name: String,
    var localtime: String,
) : Serializable

data class Current(
    var currId: Int = 0,
    var condition: Condition,
    var temp_c: Float,
    var wind_kph: Float,
    var wind_dir: String,
    var pressure_mb: Int,
    var humidity: Int,
    var vis_km: Int
) : Serializable

data class Condition(
    @PrimaryKey(autoGenerate = true)
    var conditionId: Int,
    var text: String,
    var icon: String,
    var currId: Int
) : Serializable

data class Forecast(var forecastday: ArrayList<ForecastDay> = arrayListOf()) : Serializable

data class ForecastDay(var date: String, var day: Day, var hour: ArrayList<Hour> = arrayListOf()) :
    Serializable

data class Day(
    var maxtemp_c: Double,
    var mintemp_c: Double,
    var avgtemp_c: Float,
    var condition: Condition
) : Serializable

data class Hour(var time: String, var temp_c: Double, var condition: Condition) : Serializable
