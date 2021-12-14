package com.example.domain.shared.exceptions

class InvalidTypeVehicleException(message: String = "NO SE RECONOCE EL TIPO DE VEHICULO") :
    RuntimeException(message)