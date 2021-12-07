package com.example.adn.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.Motorcycle
import com.example.usecases.GetMotorcycles
import com.example.usecases.SaveMotorcycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MotorcycleViewModel(
    private val getMotorcycles: GetMotorcycles,
    private val saveMotorcycle: SaveMotorcycle
) :
    ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _motorcycle = MutableLiveData<List<Motorcycle>>()
    val motorcycle: LiveData<List<Motorcycle>> get() = _motorcycle

    fun getListMotorCycle() {
        uiScope.launch {
            _motorcycle.value = getMotorcycles.invoke()
        }
    }

    fun saveMotorcycle(motorcycle: Motorcycle) {
        uiScope.launch {
            saveMotorcycle.invoke(motorcycle)
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}
