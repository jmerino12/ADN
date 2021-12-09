package com.example.infraestructure.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.infraestructure.database.dao.VehicleDao
import com.example.infraestructure.database.entity.Car
import com.example.infraestructure.database.entity.Motorcycle

@Database(entities = [Car::class, Motorcycle::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}
