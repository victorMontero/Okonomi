package com.android.okonomi.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.android.okonomi.R
import com.android.okonomi.extension.currencyToCad
import com.android.okonomi.model.Total
import com.android.okonomi.model.Transaction
import kotlinx.android.synthetic.main.header_item_transaction_info.view.*
import java.math.BigDecimal

class TotalView(
    context: Context,
    private val view: View,
    listTransaction: List<Transaction>
) {

    private val fullAmount: Total = Total(listTransaction)

    private val incomeColor = ContextCompat.getColor(context, R.color.income)
    private val debtColor = ContextCompat.getColor(context, R.color.debt)

    fun update() {
        addIncomeTotal()
        addDebtTotal()
        addAmountTotal()
    }

    private fun addIncomeTotal() {
        val totalIncome = fullAmount.income

        with(view.income_sum) {
            setTextColor(incomeColor)
            text = totalIncome.currencyToCad()
        }

    }


    private fun addDebtTotal() {
        val totalDebt = fullAmount.debt
        with(view.debt_sum) {
            setTextColor(debtColor)
            text = totalDebt.currencyToCad()
        }
    }

    private fun addAmountTotal() {
        val total1 = fullAmount.total
        val color = colorBy(total1)
        with(view.detail_total_id) {
            setTextColor(color)
            text = total1.currencyToCad()
        }
    }

    private fun colorBy(total1: BigDecimal): Int {
        if (total1 >= BigDecimal.ZERO) {
            return incomeColor
        } else {
            return debtColor
        }
    }

}