package com.example.weatherapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemBinding
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.WeatherViewModel


class WeatherAdapter(private val citiesList: MutableList<ForecastResponse?>) :
    RecyclerView.Adapter<WeatherAdapter.CitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        return CitiesViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val currentCity = citiesList[position]
        val context = holder.itemView.context


        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CityItemActivity::class.java)
            intent.putExtra("city", currentCity)
            holder.itemView.context.startActivity(intent)
        }

        holder.binding.starIcon.setOnClickListener {
            Toast.makeText(context, "Grad spremljen!", Toast.LENGTH_LONG).show()
        }

        holder.binding.cityCoordinates.text = context.getString(R.string.coordinatesMock)
        holder.binding.cityDistance.text = context.getString(R.string.distanceMock)
        holder.binding.cityName.text = currentCity?.location?.name
        holder.binding.apiTemperature.text =
            context.getString(R.string.currentTemperature, currentCity?.current?.temp_c.toString())
        holder.binding.starIcon.load(R.drawable.icons_android_ic_star_0)
        holder.binding.apiIcon.load(
            context.getString(
                R.string.api_icon_url,
                currentCity?.current?.condition?.icon
            )
        ) {
            error(R.drawable.ic_launcher_background)

        }

    }


    override fun getItemCount(): Int {
        return citiesList.size
    }

    class CitiesViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}