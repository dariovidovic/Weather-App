package com.example.weatherapp.data

import java.io.Serializable

data class ForecastResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast,
    val forecastday: ForecastDay,
    val day: Day,
    val hour: Hour
) : Serializable

data class Location(
    val name: String,
    val localtime: String
) : Serializable

data class Current(
    val condition: Condition,
    val temp_c: Int,
    val wind_kph: Float,
    val wind_dir: String,
    val pressure_mb: Int,
    val humidity: Int,
    val vis_km: Int
) : Serializable

data class Condition(
    val text: String,
    val icon: String
) : Serializable

data class Forecast(var forecastday: ArrayList<ForecastDay>  = arrayListOf()) : Serializable

data class ForecastDay(val date: String,val day: Day, var hour: ArrayList<Hour>) : Serializable

data class Day(val maxtemp_c: Double, val mintemp_c: Double, val avgtemp_c : Float, val condition: Condition) : Serializable

data class Hour(val time: String, val temp_c: Double, val condition: Condition) : Serializable
