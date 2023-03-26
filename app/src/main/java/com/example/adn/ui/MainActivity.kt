package com.example.adn.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.adn.R
import com.example.adn.common.MessageFactory
import com.example.adn.databinding.ActivityMainBinding
import com.example.adn.ui.car.CarFragment
import com.example.adn.ui.motorcycle.MotorcycleFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val dialogFactory = MessageFactory()
    private var myFragment: Fragment? = null


    private var rButtonCar: Boolean? = null
    private var rButtonMotorcycle: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenerRadioButton()

        binding.addVehicle.setOnClickListener {
            addVehicle()
        }

        myFragment = MotorcycleFragment()
        supportFragmentManager.beginTransaction().replace(binding.fcvVehicles.id, myFragment!!)
            .addToBackStack(null).commit()
    }

    private fun listenerRadioButton() {
        binding.vehicleGroup.setOnCheckedChangeListener { _, checkedId ->
            myFragment = null
            when (checkedId) {
                R.id.rbtnMotorcycle -> {
                    myFragment = MotorcycleFragment()
                    rButtonMotorcycle = true
                    rButtonCar = false
                }
                R.id.rbtnCar -> {
                    myFragment = CarFragment()
                    rButtonCar = true
                    rButtonMotorcycle = false
                }
            }
            disableEnableTextInputCylinder(rButtonMotorcycle)
            supportFragmentManager.beginTransaction().replace(binding.fcvVehicles.id, myFragment!!)
                .addToBackStack(null).commit()
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
//            viewModelCar.saveCar(
//                Car(
//                    binding.etPlaca.text.toString(),
//                    dateEnter = System.currentTimeMillis()
//                )
//            )
        } catch (e: Exception) {
            val errorDialog =
                dialogFactory.getDialog(this, MessageFactory.TYPE_ERROR, e.message!!)
            errorDialog.show()
        }
    }

    private fun addMotorcycle() {
        try {
//            viewModelMotorcycle.saveMotorcycle(
//                Motorcycle(
//                    binding.etPlaca.text.toString(),
//                    dateEnter = System.currentTimeMillis(),
//                    binding.etCilindraje.text.toString().toDouble(),
//                )
//            )
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