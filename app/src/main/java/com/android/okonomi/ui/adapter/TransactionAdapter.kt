package com.android.okonomi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.android.okonomi.R
import com.android.okonomi.extension.categoryStringSize
import com.android.okonomi.extension.currencyToCad
import com.android.okonomi.extension.formatToEnCa
import com.android.okonomi.model.Transaction
import com.android.okonomi.model.TypeOfTransaction
import kotlinx.android.synthetic.main.transaction_item.view.*

class TransactionAdapter(
    private val transactionList: List<Transaction>,
    private val context: Context
) : BaseAdapter() {

    private val NUMBER_CHARACTER = 14

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.transaction_item, parent, false)

        val transaction = transactionList[position]

        addTotal(transaction, createdView)
        addIcon(transaction, createdView)
        addCategory(createdView, transaction)
        addDate(createdView, transaction)

        return createdView
    }

    private fun addDate(
        createdView: View,
        transaction: Transaction
    ) {
        createdView.transaction_date_text_view.text = transaction.date
            .formatToEnCa()
    }

    private fun addCategory(
        createdView: View,
        transaction: Transaction
    ) {
        createdView.transaction_category_text_view.text = transaction.categoryOfTransaction
            .categoryStringSize(NUMBER_CHARACTER)
    }

    private fun addIcon(
        transaction: Transaction,
        createdView: View
    ) {
        val icon = iconType(transaction.typeOfTransaction)
        createdView.ic_transaction_image_view
            .setBackgroundResource(icon)
    }

    private fun iconType(type: TypeOfTransaction): Int {
        if (type == TypeOfTransaction.INCOME) {
            return R.drawable.icon_transaction_income
        }
        return R.drawable.icon_transaction_debt
    }

    private fun addTotal(
        transaction: Transaction,
        createdView: View
    ) {
        val color: Int = colorOfTransaction(transaction.typeOfTransaction)
        createdView.transaction_total_text_view
            .setTextColor(color) // set fora

        createdView.transaction_total_text_view.text = transaction.amount
            .currencyToCad()
    }

    private fun colorOfTransaction(
        type: TypeOfTransaction
    ): Int {
        if (type == TypeOfTransaction.INCOME) {
            return ContextCompat.getColor(context, R.color.income)
        }
        return ContextCompat.getColor(context, R.color.debt)
    }


    override fun getItem(position: Int): Transaction {
        return transactionList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transactionList.size
    }


}