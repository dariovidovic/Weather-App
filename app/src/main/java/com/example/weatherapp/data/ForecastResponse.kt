package com.example.weatherapp.data

data class ForecastResponse(
    val location : Location,
    val current : Current
)

data class Location(
    val name : String
)

data class Current(
    val condition : Condition,
    val temp_c : Int
)

data class Condition(
    val icon : String
)
