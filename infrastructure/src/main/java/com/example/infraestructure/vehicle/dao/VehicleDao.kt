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

    @Query("SELECT EXISTS(SELECT * FROM car WHERE licencePlate = :licensePlate)")
    suspend fun existCar(licensePlate: String): Boolean

    @Query("SELECT COUNT(*) FROM CAR")
    suspend fun totalOfCarsInParking(): Int

    @Query("SELECT * FROM motorcycle")
    suspend fun getAllMotorCycle(): List<Motorcycle>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMotorCycle(motorcycle: Motorcycle)

    @Query("SELECT EXISTS(SELECT * FROM motorcycle WHERE licencePlate = :licensePlate)")
    fun existMotorcycle(licensePlate: String): Boolean

    @Query("SELECT COUNT(*) FROM motorcycle")
    suspend fun totalOfMotorcycleInParking(): Int

    @Delete
    suspend fun deleteCar(car: Car)

    @Delete
    suspend fun deleteMotorcycle(motorcycle: Motorcycle)

}