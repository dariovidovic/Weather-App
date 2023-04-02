package com.example.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(SearchFragment())
        val navView: BottomNavigationView = binding.bottomNav
        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.search -> {
                    loadFragment(SearchFragment())
                    true

                }
                R.id.favourite -> {
                    loadFragment(MyCitiesFragment())
                    true
                }
                R.id.settings -> {
                    loadFragment(SettingsFragment())
                    true
                }
                else ->{

                }
            }
            true

        }


    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }


}