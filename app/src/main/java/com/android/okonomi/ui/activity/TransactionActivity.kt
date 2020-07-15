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
import com.android.okonomi.ui.dialog.EditTransactionDialog
import kotlinx.android.synthetic.main.activity_home_transaction.*

class TransactionActivity : AppCompatActivity() {

    private val listTransaction: MutableList<Transaction> = mutableListOf()
    private val activityView by lazy {
        window.decorView
    }

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
        AddTransactionDialog(activityView as ViewGroup, this)
            .setUpDialog(type, object : TransactionDelegate {
                override fun delegate(transaction: Transaction) {
                    listTransaction.add(transaction)
                    updateListOfTransactions()
                    transaction_list_button_id.close(true)
                }

            })
    }

    private fun callDialogIncome(type: TypeOfTransaction) {
        AddTransactionDialog(activityView as ViewGroup, this)
            .setUpDialog(type, object : TransactionDelegate {
                override fun delegate(transaction: Transaction) {
                    addTransaction(transaction)
                    transaction_list_button_id.close(true)
                }

            })
    }

    private fun addTransaction(transaction: Transaction) {
        listTransaction.add(transaction)
        updateListOfTransactions()
    }


    private fun updateListOfTransactions() {
        setUpList()
        setUpTotal()
    }

    private fun setUpTotal() {
        val totalView = TotalView(this, activityView, listTransaction)
        totalView.update()
    }

    private fun setUpList() {
        val adapterTransaction = TransactionAdapter(listTransaction, this)
        with(list_transaction_list_view){
            adapter = adapterTransaction
            setOnItemClickListener { _, _, i, _ ->
                val transaction = listTransaction[i]
                callEditDialog(transaction, i)
            }
        }
    }

    private fun callEditDialog(transaction: Transaction, i: Int) {
        EditTransactionDialog(activityView as ViewGroup, this)
            .setUpDialog(transaction, object : TransactionDelegate {
                override fun delegate(transaction: Transaction) {
                    editTransaction(transaction, i)
                }

            })
    }

    private fun editTransaction(transaction: Transaction, i: Int) {
        listTransaction[i] = transaction
        updateListOfTransactions()
    }

}