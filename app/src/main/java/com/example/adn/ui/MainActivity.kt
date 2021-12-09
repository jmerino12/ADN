package com.example.adn.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adn.R
import com.example.adn.common.MessageFactory
import com.example.adn.common.Resource
import com.example.adn.common.app
import com.example.adn.common.getViewModel
import com.example.adn.databinding.ActivityMainBinding
import com.example.adn.ui.viewmodels.CarViewModel
import com.example.adn.ui.viewmodels.MotorcycleViewModel
import com.example.adn.ui.viewmodels.VehicleAdapter
import com.example.domainlibrary.entities.Car
import com.example.domainlibrary.entities.Motorcycle
import com.example.domainlibrary.entities.Vehicle

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
        listenerRadioButton()

        binding.addVehicle.setOnClickListener {
            addVehicle()
        }
        setupRecyclerView()
        setupObserverMotorcycle()
    }

    private fun setupObserverMotorcycle() {
        viewModelMotorcycle.motorcycle.observe(this, {
            when (it) {
                is Resource.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is Resource.Content -> {
                    if (it.data.isEmpty()) Toast.makeText(
                        this,
                        "No hay carros registrados",
                        Toast.LENGTH_LONG
                    ).show()
                    adapter.submitList(it.data)
                    binding.loading.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.loading.visibility = View.GONE
                }
            }
        })
    }

    private fun setupObserverCar() {

        viewModelCar.car.observe(this, {
            when (it) {
                is Resource.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is Resource.Content -> {
                    if (it.data.isEmpty()) Toast.makeText(
                        this,
                        "No hay carros registrados",
                        Toast.LENGTH_LONG
                    ).show()
                    adapter.submitList(it.data)
                    binding.loading.visibility = View.GONE

                }
                is Resource.Error -> {
                    binding.loading.visibility = View.GONE

                }
            }
        })
    }


    private fun setupRecyclerView() {
        adapter = VehicleAdapter(::onVehicleClicked)
        binding.rvVehicles.layoutManager = LinearLayoutManager(this)
        binding.rvVehicles.adapter = adapter
    }

    private fun onVehicleClicked(vehicle: Vehicle) {
        when (vehicle) {
            is Car -> {
                dialogFactory.getDialog(
                    this,
                    MessageFactory.TYPE_INFO,
                    "El precio total a pagar por el parking es de ${vehicle.payParking()}"
                )
                    .setPositiveButton("Pagar") { dialog, _ ->
                        viewModelCar.payParking(vehicle)
                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
            is Motorcycle -> {
                dialogFactory.getDialog(
                    this,
                    MessageFactory.TYPE_INFO,
                    "El precio total a pagar por el parking es de ${vehicle.payParking()}"
                ).setPositiveButton("Pagar") { dialog, _ ->
                    viewModelMotorcycle.payParking(vehicle)
                    dialog.dismiss()
                }
                    .setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.dismiss()
                    }.show()
            }
            else -> {
                throw RuntimeException("tipo de vehiculo no declaradao")
            }
        }
    }

    private fun listenerRadioButton() {
        binding.vehicleGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbtnMotorcycle -> {
                    setupObserverMotorcycle()
                    rButtonMotorcycle = true
                    rButtonCar = false
                }
                R.id.rbtnCar -> {
                    setupObserverCar()
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