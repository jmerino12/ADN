package com.example.infraestructure.database

import com.example.domain.entities.Car
import com.example.domain.entities.Motorcycle
import com.example.infraestructure.database.entity.Car as CarRoom
import com.example.infraestructure.database.entity.Motorcycle as MotorCyclerRoom


fun Car.toRoomVehicle(): CarRoom = CarRoom(
    licencePlate = licencePlate,
    dateEnter = dateEnter
)

fun CarRoom.toDomain(): Car = Car(
    licencePlate = licencePlate,
    dateEnter = dateEnter,
)

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




