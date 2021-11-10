package com.pns.moneymanager.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pns.moneymanager.models.Model
import com.pns.moneymanager.interfaces.OnItemClickListener
import com.pns.moneymanager.R
import com.pns.moneymanager.viewholder.ViewHolder

class Adapter(
    private val list: List<Model>,
    private val context: Context,
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

        holder.itemView.setOnLongClickListener {
            showDialog(model)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun showDialog(model: Model) {

        val dialog = AlertDialog.Builder(context)
        dialog.setPositiveButton("Delete") { _, _ ->

            listener.onDeleteClick(model)

        }.setNeutralButton("Edit") { _, _ ->

            listener.onEditClick(model)
        }
        dialog.create().show()
    }
}