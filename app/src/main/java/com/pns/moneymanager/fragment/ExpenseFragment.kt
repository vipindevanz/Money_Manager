package com.pns.moneymanager.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pns.moneymanager.*
import com.pns.moneymanager.activity.AddIncomeAndExpenseActivity
import com.pns.moneymanager.adapter.Adapter
import com.pns.moneymanager.models.MoneyManagerDao
import com.pns.moneymanager.models.MoneyManagerDatabase
import com.pns.moneymanager.interfaces.OnItemClickListener
import com.pns.moneymanager.models.Model
import com.pns.moneymanager.repository.MoneyManagerRepo
import com.pns.moneymanager.viewmodels.MoneyManagerViewModel
import com.pns.moneymanager.viewmodels.MoneyManagerViewModelFactory
import kotlinx.android.synthetic.main.fragment_expense.*

class ExpenseFragment : Fragment(R.layout.fragment_expense), OnItemClickListener {

    private val expenseList = mutableListOf<Model>()
    private lateinit var db: MoneyManagerDatabase
    private lateinit var dao: MoneyManagerDao
    private lateinit var viewModel: MoneyManagerViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        db = MoneyManagerDatabase.getDatabaseObject(context)
        dao = db.dao()

        val repo = MoneyManagerRepo(dao)
        val viewModelFactory = MoneyManagerViewModelFactory(repo)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MoneyManagerViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
    }

    private fun fetchData() {

        val adapter = Adapter(expenseList, context as Activity, this)
        recyclerView.adapter = adapter

        viewModel.get().observe(viewLifecycleOwner, Observer {

            expenseList.clear()

            for (i in it) {
                if (i.type == "Expense") {
                    expenseList.add(i)
                }
            }

            adapter.notifyDataSetChanged()
        })
    }

    override fun onDeleteClick(model: Model) {

        viewModel.delete(model)
    }

    override fun onEditClick(model: Model) {
        val intent = Intent(context, AddIncomeAndExpenseActivity::class.java)
        intent.putExtra("data", model)
        startActivity(intent)
    }
}