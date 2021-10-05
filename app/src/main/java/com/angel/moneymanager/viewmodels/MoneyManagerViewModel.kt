package com.angel.moneymanager.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.angel.moneymanager.models.Model
import com.angel.moneymanager.repository.MoneyManagerRepo

class MoneyManagerViewModel(private val repo: MoneyManagerRepo) : ViewModel() {

    fun add(model: Model) {

        repo.addTaskToRoom(model)
    }

    fun get(): LiveData<List<Model>> {

        return repo.getAllData()
    }

    fun update(model: Model) {
        return repo.updateTask(model)
    }

    fun delete(model: Model) {
        return repo.deleteTask(model)
    }
}