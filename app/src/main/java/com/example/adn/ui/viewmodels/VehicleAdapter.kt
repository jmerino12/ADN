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
import com.example.domainlibrary.entities.Car
import com.example.domainlibrary.entities.Motorcycle
import com.example.domainlibrary.entities.Vehicle


class VehicleAdapter : ListAdapter<Vehicle, BaseViewHolder<*>>(
    VehicleDiffCallback
) {

    private lateinit var items: List<Vehicle>

    fun setList(list: List<Vehicle>) {
        items = list
    }

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
            1 -> {
                val viewBinding =
                    ItemVehicleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return CarViewHolder(viewBinding)
            }
            2 -> {
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
                holder.bind(items[position], position)
            }
            is MotorcycleViewHolder -> {
                holder.bind(items[position], position)
            }
            else -> {
                throw RuntimeException("ViewType no declarado ")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Car -> {
                1
            }
            is Motorcycle -> {
                2
            }
            else -> {
                throw RuntimeException("ViewType no declarado")
            }
        }
    }


    inner class CarViewHolder(
        private val binding: ItemVehicleBinding
    ) :
        BaseViewHolder<Vehicle>(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(item: Vehicle, position: Int) {
            binding.placa.text = item.licencePlate
            binding.imgFoto.setImageDrawable(
                AppCompatResources.getDrawable(
                    binding.root.context,
                    R.drawable.ic_car
                )
            )
            binding.horaEntrada.text = convertLongToTime(item.dateEnter)
        }
    }

    inner class MotorcycleViewHolder(
        private val binding: ItemVehicleBinding
    ) :
        BaseViewHolder<Vehicle>(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(item: Vehicle, position: Int) {
            binding.placa.text = item.licencePlate
            binding.imgFoto.setImageDrawable(
                AppCompatResources.getDrawable(
                    binding.root.context,
                    R.drawable.ic_bike
                )
            )
            binding.horaEntrada.text = convertLongToTime(item.dateEnter)
            binding.cilindraje.text = item.cylinderCapacity.toString()
        }
    }
}