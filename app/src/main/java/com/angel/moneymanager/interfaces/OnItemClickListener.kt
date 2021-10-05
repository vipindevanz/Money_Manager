package com.angel.moneymanager.interfaces

import com.angel.moneymanager.models.Model

interface OnItemClickListener {

    fun onDeleteClick(model: Model)
    fun onEditClick(model: Model)
}