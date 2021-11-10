package com.pns.moneymanager.interfaces

import com.pns.moneymanager.models.Model

interface OnItemClickListener {

    fun onDeleteClick(model: Model)
    fun onEditClick(model: Model)
}