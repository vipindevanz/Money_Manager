package com.pns.moneymanager.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pns.moneymanager.R
import com.pns.moneymanager.models.Model
import kotlinx.android.synthetic.main.item_layout.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val edit: ImageView = itemView.findViewById(R.id.edit)
    val delete: ImageView = itemView.findViewById(R.id.delete)

    fun setData(model: Model) {

        itemView.apply {
            amount.text = model.amount.toString()
            date.text = model.date
            description.text = model.description
        }
    }
}