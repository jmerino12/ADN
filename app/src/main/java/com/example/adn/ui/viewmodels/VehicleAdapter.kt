package com.example.adn.ui.viewmodels

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.adn.R
import com.example.adn.common.BaseViewHolder
import com.example.adn.common.convertLongToTime
import com.example.adn.databinding.ItemVehicleBinding
import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.entities.Motorcycle
import com.example.domain.vehicle.entities.Vehicle

const val VIEW_TYPE_CAR = 1
const val VIEW_TYPE_MOTORCYCLE = 2

class VehicleAdapter(private val listener: (Vehicle) -> Unit) :
    ListAdapter<Vehicle, BaseViewHolder<*>>(
        VehicleDiffCallback
    ) {

    companion object {
        private val VehicleDiffCallback = object : DiffUtil.ItemCallback<Vehicle>() {
            override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        when (viewType) {
            VIEW_TYPE_CAR -> {
                val viewBinding =
                    ItemVehicleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return CarViewHolder(viewBinding)
            }
            VIEW_TYPE_MOTORCYCLE -> {
                val viewBinding =
                    ItemVehicleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return MotorcycleViewHolder(viewBinding)
            }
            else -> throw RuntimeException("ViewType No declarado")
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is CarViewHolder -> {
                holder.bind(getItem(position) as Car, position)
            }
            is MotorcycleViewHolder -> {
                holder.bind(getItem(position) as Motorcycle, position)
            }
            else -> {
                throw RuntimeException("ViewType no declarado ")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Car -> {
                VIEW_TYPE_CAR
            }
            is Motorcycle -> {
                VIEW_TYPE_MOTORCYCLE
            }
            else -> {
                throw RuntimeException("ViewType no declarado")
            }
        }
    }


    inner class CarViewHolder(
        private val binding: ItemVehicleBinding
    ) :
        BaseViewHolder<Car>(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(item: Car, position: Int) {
            binding.placa.text = item.licencePlate
            binding.imgFoto.setImageDrawable(
                AppCompatResources.getDrawable(
                    binding.root.context,
                    R.drawable.ic_car
                )
            )
            binding.horaEntrada.text = convertLongToTime(item.dateEnter)
            binding.btnExit.setOnClickListener {
                listener(item)
            }
        }
    }

    inner class MotorcycleViewHolder(
        private val binding: ItemVehicleBinding
    ) :
        BaseViewHolder<Motorcycle>(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(item: Motorcycle, position: Int) {
            binding.placa.text = item.licencePlate
            binding.imgFoto.setImageDrawable(
                AppCompatResources.getDrawable(
                    binding.root.context,
                    R.drawable.ic_bike
                )
            )
            binding.horaEntrada.text = convertLongToTime(item.dateEnter)
            binding.cilindraje.text = item.cylinderCapacity.toString()
            binding.btnExit.setOnClickListener {
                listener(item)
            }
        }
    }
}