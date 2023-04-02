package com.example.weatherapp.data


data class SearchResponse(
    val name: String,
    val region: String

) {
    override fun toString(): String {
        return this.name + " (Region: " + this.region + ")"
    }
}
