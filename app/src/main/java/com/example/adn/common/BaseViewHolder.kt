package com.example.adn.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

//recibe un unico argumento, que es la vista que va almacenar el viewHolder
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //bindear cada uno de los elementos
    abstract fun bind(item: T, position: Int)

}