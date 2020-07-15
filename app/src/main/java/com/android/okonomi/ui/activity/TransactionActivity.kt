package com.android.okonomi.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.android.okonomi.R
import com.android.okonomi.delegate.TransactionDelegate
import com.android.okonomi.model.Transaction
import com.android.okonomi.model.TypeOfTransaction
import com.android.okonomi.ui.TotalView
import com.android.okonomi.ui.adapter.TransactionAdapter
import com.android.okonomi.ui.dialog.AddTransactionDialog
import kotlinx.android.synthetic.main.activity_home_transaction.*

class TransactionActivity : AppCompatActivity() {

    private val listTransaction: MutableList<Transaction> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_transaction)

        setUpTotal()
        setUpList()
        setUpFab()
    }

    private fun setUpFab() {
        button_add_income_id
            .setOnClickListener {
                callDialogIncome(TypeOfTransaction.INCOME)
            }
        button_add_debt_id
            .setOnClickListener {
                callDialogDebt(TypeOfTransaction.DEBT)
            }
    }

    private fun callDialogDebt(type: TypeOfTransaction) {
        AddTransactionDialog(window.decorView as ViewGroup, this)
            .setUpDialog(type, object : TransactionDelegate {
                override fun delegate(transaction: Transaction) {
                    updateListOfTransactions(transaction)
                    transaction_list_button_id.close(true)
                }

            })
    }

    private fun callDialogIncome(type: TypeOfTransaction) {
        AddTransactionDialog(window.decorView as ViewGroup, this)
            .setUpDialog(type, object : TransactionDelegate {
                override fun delegate(transaction: Transaction) {
                    updateListOfTransactions(transaction)
                    transaction_list_button_id.close(true)
                }

            })
    }


    private fun updateListOfTransactions(transaction: Transaction) {
        listTransaction.add(transaction)
        setUpList()
        setUpTotal()
    }

    private fun setUpTotal() {
        val view: View = window.decorView
        val totalView = TotalView(this, view, listTransaction)
        totalView.update()
    }

    private fun setUpList() {
        list_transaction_list_view.adapter = TransactionAdapter(listTransaction, this)
    }

}