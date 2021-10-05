package com.angel.moneymanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.angel.moneymanager.repository.MoneyManagerRepo

class MoneyManagerViewModelFactory(private val repo: MoneyManagerRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return MoneyManagerViewModel(repo) as T
    }
}