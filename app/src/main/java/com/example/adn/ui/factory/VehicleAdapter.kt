package com.example.adn.ui.factory

import android.util.Log
import androidx.recyclerview.widget.ListAdapter
import com.example.adn.common.BaseViewHolder
import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.entities.Motorcycle

import com.example.domain.vehicle.entities.Vehicle

// 1 PRODUCTO
interface VehicleAdapterProduct<T> where T : Vehicle {
    fun setupAdapter(): ListAdapter<T, BaseViewHolder<*>>
    fun onClick(vehicle: T)
    fun submitData(vehicles: List<T>)
}

// 2 PRODUCTOS CONCRETOS
class CarAdapter : VehicleAdapterProduct<Car> {
    private lateinit var carAdapter: com.example.adn.ui.car.CarAdapter

    override fun setupAdapter(): ListAdapter<Car, BaseViewHolder<*>> {
        carAdapter = com.example.adn.ui.car.CarAdapter(::onClick)
        Log.e("carAdapter", carAdapter.toString())
        return carAdapter
    }

    override fun submitData(vehicles: List<Car>) {
        carAdapter.submitList(vehicles)
    }

    override fun onClick(vehicle: Car) {
        Log.i("onClickCarAdapter", vehicle.licencePlate)
    }


}

class MotorcycleAdapter : VehicleAdapterProduct<Motorcycle> {

    private lateinit var motorcycleAdapter: com.example.adn.ui.motorcycle.MotorcycleAdapter

    override fun setupAdapter(): ListAdapter<Motorcycle, BaseViewHolder<*>> {
        motorcycleAdapter = com.example.adn.ui.motorcycle.MotorcycleAdapter(::onClick)
        Log.e("motorcycleAdapter", motorcycleAdapter.toString())
        return motorcycleAdapter
    }

    override fun submitData(vehicles: List<Motorcycle>) {
        motorcycleAdapter.submitList(vehicles)
    }

    override fun onClick(vehicle: Motorcycle) {
        Log.i("onClickMotorcycleAda", vehicle.licencePlate)
        print(vehicle.toString())
    }


}


// 3. CLASE CREADORA
abstract class Creator<T> where T : Vehicle {
    lateinit var adapter: VehicleAdapterProduct<T>

    fun renderAdapter(): ListAdapter<T, BaseViewHolder<*>> {
        adapter = createAdapter()
        Log.e("Creator", adapter.toString())
        return adapter.setupAdapter()
    }

    abstract fun createAdapter(): VehicleAdapterProduct<T>
}

// 4. CLASE CREADORA CONCRETO
class CarAdapterConcrete : Creator<Car>() {
    override fun createAdapter(): VehicleAdapterProduct<Car> {
        return CarAdapter()
    }
}

class MotorcycleAdapterConcrete : Creator<Motorcycle>() {
    override fun createAdapter(): VehicleAdapterProduct<Motorcycle> {
        return MotorcycleAdapter()
    }
}



