package com.example.adn.ui

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


class MotorcycleAdapter(private val listener: (Motorcycle) -> Unit) :
    ListAdapter<Motorcycle, BaseViewHolder<*>>(
        VehicleDiffCallback
    ) {

    companion object {
        private val VehicleDiffCallback = object : DiffUtil.ItemCallback<Motorcycle>() {
            override fun areItemsTheSame(oldItem: Motorcycle, newItem: Motorcycle): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Motorcycle, newItem: Motorcycle): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val viewBinding =
            ItemVehicleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MotorcycleViewHolder(viewBinding)

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = getItem(position)
        when (holder) {
            is MotorcycleViewHolder -> holder.bind(item, position)
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