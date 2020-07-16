package com.android.okonomi.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.android.okonomi.R
import com.android.okonomi.dao.TransactionDAO
import com.android.okonomi.model.Transaction
import com.android.okonomi.model.TypeOfTransaction
import com.android.okonomi.ui.TotalView
import com.android.okonomi.ui.adapter.TransactionAdapter
import com.android.okonomi.ui.dialog.AddTransactionDialog
import com.android.okonomi.ui.dialog.EditTransactionDialog
import kotlinx.android.synthetic.main.activity_home_transaction.*

class TransactionActivity : AppCompatActivity() {

    private val dao = TransactionDAO()
    private val listTransaction = dao.listTransaction
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
            .setUpDialog(type) {

                listTransaction.add(it)
                transaction_list_button_id.close(true)
            }
    }

    private fun callDialogIncome(type: TypeOfTransaction) {
        AddTransactionDialog(activityView as ViewGroup, this)
            .setUpDialog(type) {

                addTransaction(it)
                transaction_list_button_id.close(true)


            }
    }

    private fun addTransaction(transaction: Transaction) {
        dao.addTransaction(transaction)
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
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "delete")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val menuId = item?.itemId
        if(menuId == 1){
            val adapterMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val transactionPosition = adapterMenuInfo.position
            deleteTransaction(transactionPosition)
        }
        return super.onContextItemSelected(item)
    }

    private fun deleteTransaction(position: Int) {
        dao.deleteTransaction(position)
        updateListOfTransactions()
    }

    private fun callEditDialog(transaction: Transaction, i: Int) {
        EditTransactionDialog(activityView as ViewGroup, this)
            .setUpDialog(transaction) {

                editTransaction(it, i)

            }
    }

    private fun editTransaction(transaction: Transaction, i: Int) {
        dao.editTransaction(transaction, i)
        updateListOfTransactions()
    }

}