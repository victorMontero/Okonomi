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
    transactionList: List<Transaction>,
    context: Context
) : BaseAdapter() {

    private val transactionList = transactionList
    private val context = context

    private val NUMBER_CHARACTER = 14

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.transaction_item, parent, false)

        val transaction = transactionList[position]

        if (transaction.typeOfTransaction == TypeOfTransaction.INCOME) {
            createdView.transaction_total_text_view
                .setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
        } else {
            createdView.transaction_total_text_view
                .setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        }

        if (transaction.typeOfTransaction == TypeOfTransaction.INCOME) {
            createdView.ic_transaction_image_view
                .setBackgroundResource(R.drawable.icon_transaction_income)
        } else {
            createdView.ic_transaction_image_view
                .setBackgroundResource(R.drawable.icon_transaction_debt)
        }

        createdView.transaction_total_text_view.text = transaction.amount
            .currencyToCad()
        createdView.transaction_category_text_view.text = transaction.categoryOfTransaction
            .categoryStringSize(NUMBER_CHARACTER)
        createdView.transaction_date_text_view.text = transaction.date
            .formatToEnCa()

        return createdView
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