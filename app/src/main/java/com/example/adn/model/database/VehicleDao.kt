package com.example.adn.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

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

}