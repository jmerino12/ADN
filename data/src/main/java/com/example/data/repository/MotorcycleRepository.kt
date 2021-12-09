package com.example.datalibrary.repository

import com.example.data.source.LocalDataSource
import com.example.domainlibrary.entities.Motorcycle

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