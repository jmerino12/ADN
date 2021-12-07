package com.example.adn.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.adn.databinding.ActivityMainBinding
import com.example.domain.entities.Vehicle
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var listVehicles: MutableList<Vehicle>


    private var rButtonCar: Boolean? = null
    private var rButtonMotorcycle: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listVehicles = ArrayList()

        binding.addVehicle.setOnClickListener {

        }

    }


}