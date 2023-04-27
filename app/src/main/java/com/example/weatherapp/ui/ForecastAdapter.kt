package com.example.weatherapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.databinding.WeatherItemBinding
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(private val forecastInfo: ForecastResponse?, private val dataType: String) :
    RecyclerView.Adapter<ForecastAdapter.ForViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForViewHolder {
        return ForViewHolder(
            WeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ForViewHolder, position: Int) {
        val context = holder.itemView.context


        if (dataType == "currentDay") {
            holder.binding.currentHour.text =
                forecastInfo?.forecast?.forecastday?.get(0)?.hour?.get(position)?.time?.subSequence(
                    11,
                    16
                ).toString()


            holder.binding.currentHourIcon.load(
                context.getString(
                    R.string.api_icon_url, forecastInfo?.forecast?.forecastday?.get(0)?.hour?.get(
                        position
                    )?.condition?.icon
                )
            )

            holder.binding.currentHourTemperature.text = context.getString(
                R.string.currentTemperature,
                forecastInfo?.forecast?.forecastday?.get(0)?.hour?.get(position)?.temp_c.toString()
            )
        } else {
            holder.binding.currentHour.text =
                forecastInfo?.forecast?.forecastday?.get(position)?.date?.subSequence(
                    5,
                    10
                ).toString()
            holder.binding.currentHourIcon.load(
                context.getString(
                    R.string.api_icon_url,
                    forecastInfo?.forecast?.forecastday?.get(position)?.day?.condition?.icon
                )
            )
            holder.binding.currentHourTemperature.text = context.getString(
                R.string.currentTemperature,
                forecastInfo?.forecast?.forecastday?.get(position)?.day?.avgtemp_c.toString()
            )
        }
    }

    override fun getItemCount(): Int {
        return if (dataType == "currentDay") forecastInfo?.forecast?.forecastday?.get(0)?.hour?.size
            ?: 24
        else forecastInfo?.forecast?.forecastday?.size ?: 3
    }

    class ForViewHolder(val binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root)


}