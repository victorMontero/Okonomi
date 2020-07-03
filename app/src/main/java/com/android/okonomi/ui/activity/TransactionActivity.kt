package com.android.okonomi.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.okonomi.R
import com.android.okonomi.model.Transaction
import com.android.okonomi.model.TypeOfTransaction
import com.android.okonomi.ui.adapter.TransactionAdapter
import kotlinx.android.synthetic.main.activity_home_transaction.*
import java.math.BigDecimal

class TransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_transaction)

        val listTransaction: List<Transaction> = createsExamplesTransactions()

        setUpList(listTransaction)
    }

    private fun setUpList(listTransaction: List<Transaction>) {
        list_transaction_list_view.adapter = TransactionAdapter(listTransaction, this)
    }

    private fun createsExamplesTransactions(): List<Transaction> {
        return listOf(
            Transaction(
                amount = BigDecimal(20.5),
                categoryOfTransaction = "lunch",
                typeOfTransaction = TypeOfTransaction.DEBT
            ),
            Transaction(
                amount = BigDecimal(100.00),
                categoryOfTransaction = "refund",
                typeOfTransaction = TypeOfTransaction.INCOME
            ),
            Transaction(
                amount = BigDecimal(112.00),
                categoryOfTransaction = "farmer's market",
                typeOfTransaction = TypeOfTransaction.DEBT
            )
        )
    }
}