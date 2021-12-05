package com.pns.moneymanager.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.pns.moneymanager.R
import com.pns.moneymanager.models.MoneyManagerDao
import com.pns.moneymanager.models.MoneyManagerDatabase
import com.pns.moneymanager.repository.MoneyManagerRepo
import com.pns.moneymanager.viewmodels.MoneyManagerViewModel
import com.pns.moneymanager.viewmodels.MoneyManagerViewModelFactory
import kotlinx.android.synthetic.main.fragment_balance.*

class BalanceFragment : Fragment(R.layout.fragment_balance) {

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

        showData()
    }

    @SuppressLint("SetTextI18n")
    private fun showData() {

        var incomeCount = 0
        var expenseCount = 0

        viewModel.get().observe(viewLifecycleOwner, {

            for (i in it) {

                if (i.type == "Income") {

                    incomeCount += i.amount

                } else {

                    expenseCount += i.amount
                }
            }

            val totalAmt = incomeCount - expenseCount
            income.text = "Income : $incomeCount"
            expense.text = "Expense : $expenseCount"
            total.text = "Total : $totalAmt"

            if (totalAmt == 0){
                profit.visibility = View.GONE
                loss.visibility = View.GONE
            } else if (totalAmt > 0){
                profit.visibility = View.VISIBLE
                loss.visibility = View.GONE
            } else{
                profit.visibility = View.GONE
                loss.visibility = View.VISIBLE
            }
        })
    }
}