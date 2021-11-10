package com.pns.moneymanager.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pns.moneymanager.models.Model
import kotlinx.android.synthetic.main.item_layout.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setData(model: Model) {

        itemView.apply {
            amount.text = model.amount.toString()
            date.text = model.date
            description.text = model.description
        }
    }
}