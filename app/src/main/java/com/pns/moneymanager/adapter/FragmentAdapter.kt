package com.pns.moneymanager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.pns.moneymanager.fragment.BalanceFragment
import com.pns.moneymanager.fragment.ExpenseFragment
import com.pns.moneymanager.fragment.IncomeFragment

class MyAdapter(fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return IncomeFragment()
            }
            1 -> {
                return ExpenseFragment()
            }
            2 -> {
                // val movieFragment = MovieFragment()
                return BalanceFragment()
            }
            else -> return IncomeFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}