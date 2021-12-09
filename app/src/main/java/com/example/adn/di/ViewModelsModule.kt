package com.example.adn.di


import com.example.adn.ui.viewmodels.CarViewModel
import com.example.adn.ui.viewmodels.MotorcycleViewModel
import com.example.domain.services.CarService
import com.example.usecases.GetMotorcycles
import com.example.usecases.PayMotorcycleParking
import com.example.usecases.SaveMotorcycle
import dagger.Module
import dagger.Provides

@Module
class ViewModelsModule {
    @Provides
    fun carViewModelProvider(carService: CarService) =
        CarViewModel(carService)

    @Provides
    fun motorcycleViewModelProvider(
        getMotorcycles: GetMotorcycles,
        saveMotorcycle: SaveMotorcycle,
        payMotorcycleParking: PayMotorcycleParking
    ) =
        MotorcycleViewModel(getMotorcycles, saveMotorcycle, payMotorcycleParking)


}