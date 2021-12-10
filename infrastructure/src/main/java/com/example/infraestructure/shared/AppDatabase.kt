package com.example.infraestructure.shared

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.infraestructure.vehicle.dao.VehicleDao
import com.example.infraestructure.vehicle.entity.Car
import com.example.infraestructure.vehicle.entity.Motorcycle

@Database(entities = [Car::class, Motorcycle::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}
