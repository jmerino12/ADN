package com.example.infraestructure.mapper

import com.example.domain.entities.Motorcycle
import com.example.infraestructure.database.entity.Motorcycle as MotorCyclerRoom

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




