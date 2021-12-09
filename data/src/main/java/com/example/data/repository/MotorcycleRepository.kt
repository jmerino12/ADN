package com.example.datalibrary.repository

import com.example.domainlibrary.entities.Motorcycle
import com.example.domainlibrary.repositories.LocalDataSource

class MotorcycleRepository(
    private val localDataSource: LocalDataSource
) {
    suspend fun getMotorcycles(): List<Motorcycle> {
        return localDataSource.getListMotorcycle()
    }

    suspend fun saveMotorcycle(motorcycle: Motorcycle) = localDataSource.saveMotorcycle(motorcycle)

    suspend fun payParking(motorcycle: Motorcycle) =
        localDataSource.payParkingMotorcycle(motorcycle)

}