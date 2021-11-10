package com.pns.moneymanager.repository

import androidx.lifecycle.LiveData
import com.pns.moneymanager.models.Model
import com.pns.moneymanager.models.MoneyManagerDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoneyManagerRepo(private val moneyManagerDao: MoneyManagerDao) {

    fun addTaskToRoom(model: Model) {
        CoroutineScope(Dispatchers.IO).launch {
            moneyManagerDao.add(model)
        }
    }

    fun getAllData(): LiveData<List<Model>> {

        return moneyManagerDao.get()
    }

    fun updateTask(model: Model) {

        CoroutineScope(Dispatchers.IO).launch {
            moneyManagerDao.update(model)
        }
    }

    fun deleteTask(model: Model) {

        CoroutineScope(Dispatchers.IO).launch {
            moneyManagerDao.delete(model)
        }
    }
}