package com.android.okonomi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.android.okonomi.R

class TransactionAdapter(
    transactionList: List<String>,
    context: Context
) : BaseAdapter() {

    private val transactionList = transactionList
    private val context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.transaction_item, parent, false)
    }

    override fun getItem(position: Int): String {
        return transactionList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transactionList.size
    }
}