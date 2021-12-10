package com.example.infraestructure.vehicle.anticorruption

import com.example.domain.vehicle.entities.Motorcycle
import com.example.infraestructure.vehicle.entity.Motorcycle as MotorCyclerRoom

fun Motorcycle.toRoomVehicle(): MotorCyclerRoom = MotorCyclerRoom(
    cylinderCapacity = cylinderCapacity,
    licencePlate = licencePlate,
    dateEnter = dateEnter
)

fun MotorCyclerRoom.toDomain(): Motorcycle = Motorcycle(
    licencePlate = licencePlate,
    cylinderCapacity = cylinderCapacity,
    dateEnter = dateEnter
)




