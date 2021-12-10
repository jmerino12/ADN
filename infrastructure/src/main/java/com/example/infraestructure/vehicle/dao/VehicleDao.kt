package com.example.infraestructure.vehicle.dao

import androidx.room.*
import com.example.infraestructure.vehicle.entity.Car
import com.example.infraestructure.vehicle.entity.Motorcycle

@Dao
interface VehicleDao {

    @Query("SELECT * FROM car")
    suspend fun getAllCars(): List<Car>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCar(car: Car)


    @Query("SELECT * FROM motorcycle")
    suspend fun getAllMotorCycle(): List<Motorcycle>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMotorCycle(motorcycle: Motorcycle)

    @Delete
    suspend fun deleteCar(car: Car)

    @Delete
    suspend fun deleteMotorcycle(motorcycle: Motorcycle)

}