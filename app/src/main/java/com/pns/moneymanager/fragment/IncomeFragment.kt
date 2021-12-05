package com.pns.moneymanager.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.pns.moneymanager.*
import com.pns.moneymanager.activity.AddIncomeAndExpenseActivity
import com.pns.moneymanager.adapter.Adapter
import com.pns.moneymanager.interfaces.OnItemClickListener
import com.pns.moneymanager.models.Model
import com.pns.moneymanager.models.MoneyManagerDao
import com.pns.moneymanager.models.MoneyManagerDatabase
import com.pns.moneymanager.repository.MoneyManagerRepo
import com.pns.moneymanager.viewmodels.MoneyManagerViewModel
import com.pns.moneymanager.viewmodels.MoneyManagerViewModelFactory
import kotlinx.android.synthetic.main.fragment_income.*

class IncomeFragment : Fragment(R.layout.fragment_income), OnItemClickListener {

    private val incomeList = mutableListOf<Model>()
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

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchData() {

        val adapter = Adapter(incomeList, this)
        recyclerView.adapter = adapter

        viewModel.get().observe(viewLifecycleOwner, {

            incomeList.clear()

            for (i in it) {
                if (i.type == "Income") {
                    incomeList.add(i)
                }
            }

            adapter.notifyDataSetChanged()
        })
    }

    override fun onDeleteClick(model: Model) {

        viewModel.delete(model)
        Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onEditClick(model: Model) {

        val intent = Intent(context, AddIncomeAndExpenseActivity::class.java)
        intent.putExtra("data", model)
        startActivity(intent)
    }
}