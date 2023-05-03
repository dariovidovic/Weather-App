package com.example.weatherapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemBinding
import com.example.weatherapp.data.ForecastResponse
import java.util.*


class WeatherAdapter(private val onClickListener : OnClickListener) :

    RecyclerView.Adapter<WeatherAdapter.CitiesViewHolder>() {
    private var citiesList: MutableList<ForecastResponse?> = arrayListOf()
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

        holder.binding.cityCoordinates.text = context.getString(R.string.coordinatesMock)
        holder.binding.cityDistance.text = context.getString(R.string.distanceMock)
        holder.binding.cityName.text = currentCity?.location?.name

        if (currentCity?.isFavourite == false) {
            holder.binding.starIcon.load(R.drawable.ic_icons_android_ic_star_0)
        } else
            holder.binding.starIcon.load(R.drawable.ic_icons_android_ic_star_1)


        holder.binding.starIcon.setOnClickListener {
            if (currentCity?.isFavourite == true) {
                Toast.makeText(context, "City removed from My Cities!", Toast.LENGTH_SHORT).show()
                holder.binding.starIcon.load(R.drawable.ic_icons_android_ic_star_0)
                onClickListener.onClick(currentCity)
                notifyItemChanged(position)

            } else {
                Toast.makeText(context, "City saved to My Cities!", Toast.LENGTH_SHORT).show()
                holder.binding.starIcon.load(R.drawable.ic_icons_android_ic_star_1)
                onClickListener.onClick(currentCity)
                notifyItemChanged(position)
            }

        }

        holder.binding.apiTemperature.text =
            context.getString(R.string.currentTemperature, currentCity?.current?.temp_c.toString())

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

    class CitiesViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(city: MutableList<ForecastResponse?>) {
        this.citiesList = city
        notifyDataSetChanged()
    }

    class OnClickListener(val clickListener: (currentCity : ForecastResponse?) -> Unit){
        fun onClick(currentCity: ForecastResponse?) = clickListener(currentCity)
    }

}