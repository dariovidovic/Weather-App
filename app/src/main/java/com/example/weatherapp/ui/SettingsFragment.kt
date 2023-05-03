package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.databinding.FragmentSettingsBinding
import com.example.weatherapp.viewmodel.WeatherViewModel


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.clearMyCitiesListButton.setOnClickListener {
            clearMyFavCities()
        }

        binding.clearRecentSearchesButton.setOnClickListener {
            clearMyRecents()
        }

        return binding.root
    }

    companion object {

        fun newInstance() = SettingsFragment()

    }

    fun clearMyFavCities(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteFavCities()
            Toast.makeText(
                requireContext(),
                "Successfully deleted all the cities from the favourites!",
                Toast.LENGTH_LONG
            ).show()

        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Clear the favourite cities?")
        builder.setMessage("Are you sure you want to clear the favourite cities?")
        builder.create().show()
    }

    fun clearMyRecents(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteRecentCities()
            Toast.makeText(
                requireContext(),
                "Successfully deleted all the cities from the recents!",
                Toast.LENGTH_LONG
            ).show()

        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Clear the recents cities?")
        builder.setMessage("Are you sure you want to clear the recent cities?")
        builder.create().show()
    }
}