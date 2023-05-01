package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.example.weatherapp.databinding.FragmentMyCitiesBinding


class MyCitiesFragment : Fragment() {

    private var _binding: FragmentMyCitiesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMyCitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = WeatherAdapter()
        binding.recyclerViewTest.adapter = adapter
        binding.recyclerViewTest.layoutManager = linearLayoutManager

        viewModel.readAllData.observe(viewLifecycleOwner) {
            adapter.setData(it.toMutableList())
        }

        return root

    }

    companion object {

        fun newInstance() = MyCitiesFragment()
    }

}