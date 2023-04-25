package com.example.weatherapp.ui

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.weatherapp.R
import com.example.weatherapp.databinding.WeatherInfoTileBinding

class GridViewAdapter(
    private val applicationContext: Context,
    private val values: ArrayList<String>
) : BaseAdapter() {

    private val imagesArray = intArrayOf(
        R.drawable.icons_android_ic_thermostat,
        R.drawable.icons_android_ic_wind,
        R.drawable.icons_android_ic_humidity,
        R.drawable.icons_android_ic_pressure,
        R.drawable.icons_android_ic_visibility,
        R.drawable.icons_android_ic_accuracy
    )

    private val textValuesArray =
        intArrayOf(
            R.string.min_max,
            R.string.wind,
            R.string.humidity,
            R.string.pressure,
            R.string.visibility,
            R.string.accuracy
        )


    override fun getCount(): Int {
        return values.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val layoutInflater =
            applicationContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = WeatherInfoTileBinding.inflate(layoutInflater)
        binding.weatherTileIcon.setImageResource(imagesArray[p0])
        binding.weatherTileText.text = applicationContext.getString(textValuesArray[p0])
        binding.weatherTileValue.text = values[p0]
        return binding.root

    }
}