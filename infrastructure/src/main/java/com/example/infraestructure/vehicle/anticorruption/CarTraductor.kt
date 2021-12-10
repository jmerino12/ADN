package com.example.infraestructure.vehicle.anticorruption

import com.example.domain.vehicle.entities.Car
import com.example.infraestructure.vehicle.entity.Car as CarRoom

fun Car.toRoomVehicle(): CarRoom = CarRoom(
    licencePlate = licencePlate,
    dateEnter = dateEnter
)

fun CarRoom.toDomain(): Car = Car(
    licencePlate = licencePlate,
    dateEnter = dateEnter,
)