package com.example.weatherapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastResponse



class WeatherAdapter(private val citiesList : MutableList<ForecastResponse?>) : RecyclerView.Adapter<WeatherAdapter.CitiesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return CitiesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val currentCity = citiesList[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CityItemActivity::class.java)
            intent.putExtra("city", currentCity)
            holder.itemView.context.startActivity(intent)
        }
        val context = holder.itemView.context
        holder.cityCoordinates.text = context.getString(R.string.coordinatesMock)
        holder.cityDistance.text = context.getString(R.string.distanceMock)
        holder.cityName.text = currentCity?.location?.name
        holder.apiTemperature.text = currentCity?.current?.temp_c.toString()+"°C"
        holder.starIcon.load(R.drawable.icons_android_ic_star_0)
        holder.apiIcon.load("https:"+currentCity?.current?.condition?.icon){
            error(R.drawable.ic_launcher_background)

        }

    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName : TextView = itemView.findViewById(R.id.city_name)
        val cityCoordinates : TextView = itemView.findViewById(R.id.city_coordinates)
        val cityDistance : TextView = itemView.findViewById(R.id.city_distance)
        val apiTemperature : TextView = itemView.findViewById(R.id.api_temperature)
        val apiIcon : ImageView = itemView.findViewById(R.id.api_icon)
        val starIcon : ImageView = itemView.findViewById(R.id.star_icon)
    }
}