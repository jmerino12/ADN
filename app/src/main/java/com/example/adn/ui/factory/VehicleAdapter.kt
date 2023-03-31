package com.example.adn.ui.factory

import android.util.Log
import androidx.recyclerview.widget.ListAdapter
import com.example.adn.common.BaseViewHolder
import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.entities.Motorcycle

import com.example.domain.vehicle.entities.Vehicle

// 1 PRODUCTO
interface VehicleAdapterProduct<TVehicle, TViewHolder> where TVehicle : Vehicle, TViewHolder: BaseViewHolder<TVehicle>  {
    fun setupAdapter(): ListAdapter<TVehicle, TViewHolder>
    fun onClick(vehicle: TVehicle)
    fun submitData(vehicles: List<TVehicle>)
}

// 2 PRODUCTOS CONCRETOS
class CarAdapter : VehicleAdapterProduct<Car,BaseViewHolder<Car>  > {
    private lateinit var carAdapter: com.example.adn.ui.car.CarAdapter


    override fun submitData(vehicles: List<Car>) {
        carAdapter.submitList(vehicles)
    }

    override fun onClick(vehicle: Car) {
        Log.i("onClickCarAdapter", vehicle.licencePlate)
    }

    override fun setupAdapter(): ListAdapter<Car, BaseViewHolder<Car>> {
        carAdapter = com.example.adn.ui.car.CarAdapter(::onClick)
        Log.e("carAdapter", carAdapter.toString())
        return carAdapter
    }


}

class MotorcycleAdapter : VehicleAdapterProduct<Motorcycle, BaseViewHolder<Motorcycle>> {

    private lateinit var motorcycleAdapter: com.example.adn.ui.motorcycle.MotorcycleAdapter

    override fun setupAdapter(): ListAdapter<Motorcycle, BaseViewHolder<Motorcycle>> {
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
abstract class Creator<TVehicle, TViewHolder> where TVehicle : Vehicle,TViewHolder: BaseViewHolder<TVehicle>   {
    lateinit var adapter: VehicleAdapterProduct<TVehicle, TViewHolder>

    fun renderAdapter(): ListAdapter<TVehicle, TViewHolder> {
        adapter = createAdapter()
        Log.e("Creator", adapter.toString())
        return adapter.setupAdapter()
    }

    abstract fun createAdapter(): VehicleAdapterProduct<TVehicle, TViewHolder>
}

// 4. CLASE CREADORA CONCRETO
class CarAdapterConcrete : Creator<Car, BaseViewHolder<Car>>() {
    override fun createAdapter(): VehicleAdapterProduct<Car, BaseViewHolder<Car>> {
        return CarAdapter()
    }
}

class MotorcycleAdapterConcrete : Creator<Motorcycle, BaseViewHolder<Motorcycle>>() {
    override fun createAdapter(): VehicleAdapterProduct<Motorcycle, BaseViewHolder<Motorcycle>> {
        return MotorcycleAdapter()
    }
}



