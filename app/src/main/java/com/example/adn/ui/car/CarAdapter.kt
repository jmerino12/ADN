package com.example.adn.ui.car

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

class CarAdapter(private val listener: (Car) -> Unit) :
    ListAdapter<Car, BaseViewHolder<*>>(
        VehicleDiffCallback
    ) {

    companion object {
        private val VehicleDiffCallback = object : DiffUtil.ItemCallback<Car>() {
            override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
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
        return CarViewHolder(viewBinding)

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = getItem(position)
        when (holder) {
            is CarViewHolder -> holder.bind(item, position)
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

}