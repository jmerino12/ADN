package com.example.adn.ui.motorcycle

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
import com.example.adn.databinding.FragmentMotorcycleBinding
import com.example.adn.ui.factory.Creator
import com.example.adn.ui.factory.MotorcycleAdapterConcrete
import com.example.adn.ui.viewmodels.MotorcycleViewModel
import com.example.domain.vehicle.entities.Motorcycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MotorcycleFragment : Fragment() {
    private var _binding: FragmentMotorcycleBinding? = null
    private val binding get() = _binding!!
    private var dialogFactory: MessageFactory? = null
    private var motorcycleFactory: Creator<Motorcycle>? = null

    private val viewModelMotorcycle: MotorcycleViewModel by lazy { getViewModel { context?.app?.component!!.motorcycleViewModel } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMotorcycleBinding.inflate(inflater, container, false)
        dialogFactory = MessageFactory()
        setupRecyclerView()
        setupObserverMotorcycle()
        return binding.root
    }


    private fun setupRecyclerView() {
        motorcycleFactory = MotorcycleAdapterConcrete()
        binding.rvMotorcycle.layoutManager = LinearLayoutManager(context)
        binding.rvMotorcycle.adapter = motorcycleFactory!!.renderAdapter()
    }

    private fun setupObserverMotorcycle() {
        lifecycleScope.launch {
            viewModelMotorcycle.uiState.collect(::update)
        }
    }

    private fun update(model: UiState<List<Motorcycle>>) {
        binding.loading.visibility = if (model is UiState.Loading) View.VISIBLE else View.GONE

        if (model is UiState.Success) {
            if (model.data.isEmpty()) Toast.makeText(
                context,
                "No hay vehiculos registrados",
                Toast.LENGTH_LONG
            ).show()
            motorcycleFactory!!.adapter.submitData(model.data)
        } else if (model is UiState.Error) {
            if (model.error != null) {
                val errorDialog =
                    dialogFactory!!.getDialog(
                        requireContext(),
                        MessageFactory.TYPE_ERROR,
                        model.error.message!!
                    )
                errorDialog.setPositiveButton("Ok") { dialog, _ ->

                    viewModelMotorcycle.clearMessageError()
                    dialog.dismiss()
                }
                errorDialog.show()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogFactory = null
        motorcycleFactory = null
        _binding = null
    }
}