package com.example.adn.ui.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adn.common.MessageFactory
import com.example.adn.common.UiState
import com.example.adn.common.app
import com.example.adn.common.getViewModel
import com.example.adn.databinding.FragmentCarBinding
import com.example.adn.ui.factory.CarAdapterConcrete
import com.example.adn.ui.factory.Creator
import com.example.adn.ui.viewmodels.CarViewModel
import com.example.domain.vehicle.entities.Car
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CarFragment : Fragment() {

    private var dialogFactory: MessageFactory? = null
    private var _binding: FragmentCarBinding? = null
    private val binding get() = _binding!!


    private var carFactory: Creator<Car>? = null
    private val viewModelCar: CarViewModel by lazy { getViewModel { context?.app?.component!!.carViewModel } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCarBinding.inflate(inflater, container, false)
        dialogFactory = MessageFactory()
        setupRecyclerView()
        setupObserverCar()
        return binding.root
    }

    private fun setupRecyclerView() {
        carFactory = CarAdapterConcrete()
        binding.rvCar.layoutManager = LinearLayoutManager(context)
        binding.rvCar.adapter = carFactory!!.renderAdapter()
    }

    private fun setupObserverCar() {
        lifecycleScope.launch {
            viewModelCar.uiState.collect(::update)
        }
    }

    private fun update(model: UiState<List<Car>>) {
        binding.loading.visibility = if (model is UiState.Loading) View.VISIBLE else View.GONE

        if (model is UiState.Success) {
            if (model.data.isEmpty()) Toast.makeText(
                context,
                "No hay vehiculos registrados",
                Toast.LENGTH_LONG
            ).show()
            carFactory!!.adapter.submitData(model.data)
        } else if (model is UiState.Error) {
            if (model.error != null) {
                val errorDialog =
                    dialogFactory!!.getDialog(
                        requireContext(),
                        MessageFactory.TYPE_ERROR,
                        model.error.message!!
                    )
                errorDialog.setPositiveButton("Ok") { dialog, _ ->

                    viewModelCar.clearMessageError()
                    dialog.dismiss()
                }
                errorDialog.show()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogFactory = null
        carFactory = null
        _binding = null
    }

}