package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.domain.entities.Motorcycle

class MotorcycleRepository(
    private val localDataSource: LocalDataSource
) {
    suspend fun getMotorcycles(): List<Motorcycle> {
        return localDataSource.getListMotorcycle()
    }

    suspend fun saveMotorcycle(motorcycle: Motorcycle) = localDataSource.saveMotorcycle(motorcycle)
}