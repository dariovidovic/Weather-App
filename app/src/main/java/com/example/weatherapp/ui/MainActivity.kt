package com.example.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import androidx.activity.viewModels
import com.example.weatherapp.data.SearchResponse
import com.example.weatherapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainActivityViewModel by viewModels()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.makeApiCall()
        binding.testView.text = viewModel.getCities().value?.get(0).toString()


    }

}