package com.android.okonomi.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.android.okonomi.R
import com.android.okonomi.extension.currencyToCad
import com.android.okonomi.model.Total
import com.android.okonomi.model.Transaction
import com.android.okonomi.model.TypeOfTransaction
import kotlinx.android.synthetic.main.header_item_transaction_info.view.*
import java.math.BigDecimal

class TotalView (private val context: Context,
                 private val view: View,
                 listTransaction: List<Transaction>) {

    private val total: Total = Total(listTransaction)

    fun addIncomeTotal() {
        val totalIncome = total.income()
        view.income_sum.
        setTextColor(ContextCompat.getColor(context, R.color.income))
        view.income_sum.text = totalIncome.currencyToCad()
    }

    fun addDebtTotal() {
        val totalDebt = total.debt()
        view.debt_sum.
        setTextColor(ContextCompat.getColor(context, R.color.debt))
        view.debt_sum.text = totalDebt.currencyToCad()
    }

    fun addAmountTotal(){
        val total1 = total.total()
        if(total1.compareTo(BigDecimal.ZERO) >= 0){
            view.detail_total_id.setTextColor(ContextCompat.getColor(context, R.color.income))
        } else{
            view.detail_total_id.setTextColor(ContextCompat.getColor(context, R.color.debt))
        }
        view.detail_total_id.text = total1.currencyToCad()
    }

}