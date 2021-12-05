package com.pns.moneymanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pns.moneymanager.R
import com.pns.moneymanager.interfaces.OnItemClickListener
import com.pns.moneymanager.models.Model
import com.pns.moneymanager.viewholder.ViewHolder

class Adapter(
    private val list: List<Model>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.setData(model)

        holder.delete.setOnClickListener { listener.onDeleteClick(model) }
        holder.edit.setOnClickListener { listener.onEditClick(model) }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}