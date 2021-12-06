package com.example.adn.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.domain.entities.Motorcycle
import com.example.usecases.GetMotorcycles
import com.example.usecases.SaveMotorcycle
import kotlinx.coroutines.*

class MotorcycleViewModel(
    private val getMotorcycles: GetMotorcycles,
    private val saveMotorcycle: SaveMotorcycle
) :
    ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getListMotorCycle() = liveData(Dispatchers.IO) {
        emit(getMotorcycles.invoke())
    }

    fun saveMotorcycle(motorcycle: Motorcycle) {
        uiScope.launch {
            saveMotorcycle.invoke(motorcycle)
        }
    }

    override fun onCleared() {
        uiScope.cancel()
        viewModelJob.cancel()
        super.onCleared()
    }
}
