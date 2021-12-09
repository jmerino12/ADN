package com.example.adn.di

//import com.example.infraestructure.database.repository.MotorcycleRepository as MotorcycleImp
import com.example.domain.repositories.CarRepository
import com.example.domain.repositories.MotorcycleRepository
import com.example.domain.services.CarService
import com.example.domain.services.MotorcycleService
import dagger.Module
import dagger.Provides
import com.example.infraestructure.database.repository.CarRepository as CarRepositoryImp


@Module
class InfrastructureModule {
    @Provides
    fun carRepositoryProvider(
        carRepository: CarRepository
    ) = CarRepositoryImp(carRepository)

    /*@Provides
    fun motorcycleRepositoryProvider(
        motorcycleRepository: MotorcycleRepository
    ) = MotorcycleImp(motorcycleRepository)*/

    @Provides
    fun carServiceProvider(carRepository: CarRepository) = CarService(carRepository)

    @Provides
    fun motorcycleServiceProvider(motorcycleRepository: MotorcycleRepository) =
        MotorcycleService(motorcycleRepository)

}