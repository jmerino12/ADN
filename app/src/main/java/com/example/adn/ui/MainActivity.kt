package com.example.adn.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adn.R
import com.example.adn.databinding.ActivityMainBinding
import com.example.adn.ui.common.MessageFactory
import com.example.adn.ui.common.app
import com.example.adn.ui.common.getViewModel
import com.example.adn.ui.viewmodels.CarViewModel
import com.example.adn.ui.viewmodels.MotorcycleViewModel
import com.example.adn.ui.viewmodels.VehicleAdapter
import com.example.domain.entities.Car
import com.example.domain.entities.Motorcycle
import com.example.domain.entities.Vehicle

class MainActivity : AppCompatActivity() {

    private val viewModelCar: CarViewModel by lazy { getViewModel { app.component.carViewModel } }
    private val viewModelMotorcycle: MotorcycleViewModel by lazy { getViewModel { app.component.motorcycleViewModel } }
    private lateinit var adapter: VehicleAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var listVehicles: MutableList<Vehicle>

    private val dialogFactory = MessageFactory()


    private var rButtonCar: Boolean? = null
    private var rButtonMotorcycle: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listVehicles = ArrayList()
        setupRecyclerView()
        listenerRadioButton()
        binding.addVehicle.setOnClickListener {
            addVehicle()
        }

        viewModelMotorcycle.getListMotorCycle().observe(this, {
            listVehicles.addAll(it)
        })
        viewModelCar.getListCar().observe(this, {
            listVehicles.addAll(it)
        })

        adapter.setList(listVehicles)
        adapter.submitList(listVehicles)

    }

    override fun onResume() {
        super.onResume()
    }

    private fun setupRecyclerView() {
        adapter = VehicleAdapter()
        binding.rvVehicles.layoutManager = LinearLayoutManager(this)
        binding.rvVehicles.adapter = adapter


    }

    private fun listenerRadioButton() {
        binding.vehicleGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbtnMotorcycle -> {
                    rButtonMotorcycle = true
                    rButtonCar = false
                }
                R.id.rbtnCar -> {
                    rButtonCar = true
                    rButtonMotorcycle = false
                }
            }
            disableEnableTextInputCylinder(rButtonMotorcycle)
        }
    }

    private fun disableEnableTextInputCylinder(status: Boolean) {
        if (status) {
            binding.tilCilindraje.visibility = View.VISIBLE
        } else {
            binding.tilCilindraje.visibility = View.GONE
        }
    }

    private fun addVehicle() {
        binding.addVehicle.setOnClickListener {
            if (validateInputs()) {
                when {
                    rButtonCar == true -> {
                        addCar()
                    }
                    rButtonMotorcycle -> {
                        addMotorcycle()
                    }
                    else -> {
                        val infoDialog =
                            dialogFactory.getDialog(
                                this,
                                MessageFactory.TYPE_INFO,
                                "Llene el formulario"
                            )
                        infoDialog.show()
                    }
                }
            } else {
                val infoDialog =
                    dialogFactory.getDialog(this, MessageFactory.TYPE_INFO, "Llene el formulario")
                infoDialog.show()
            }
        }
    }

    private fun addCar() {
        try {
            viewModelCar.saveCar(
                Car(
                    binding.etPlaca.text.toString(),
                    dateEnter = System.currentTimeMillis()
                )
            )
        } catch (e: Exception) {
            val errorDialog =
                dialogFactory.getDialog(this, MessageFactory.TYPE_ERROR, e.message!!)
            errorDialog.show()
        }
    }

    private fun addMotorcycle() {
        try {
            viewModelMotorcycle.saveMotorcycle(
                Motorcycle(
                    binding.etPlaca.text.toString(),
                    binding.etCilindraje.text.toString().toDouble(),
                    dateEnter = System.currentTimeMillis()
                )
            )
        } catch (e: Exception) {
            val errorDialog =
                dialogFactory.getDialog(this, MessageFactory.TYPE_ERROR, e.message!!)
            errorDialog.show()
        }
    }


    private fun validateInputs(): Boolean {
        return validateInputLicensePlate() && validateTypeVehicle()
    }

    private fun validateInputLicensePlate(): Boolean {
        return binding.etPlaca.text!!.isNotEmpty()
    }

    private fun validateTypeVehicle(): Boolean {
        return if (binding.rbtnCar.isChecked) {
            true
        } else binding.rbtnMotorcycle.isChecked
    }
}