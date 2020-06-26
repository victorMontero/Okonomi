package com.android.okonomi.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.okonomi.R
import com.android.okonomi.ui.adapter.TransactionAdapter
import kotlinx.android.synthetic.main.activity_home_transaction.*

class TransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_transaction)

        val listTransaction = listOf("lunch - $20", "refund - $100")

        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, listTransaction
        )

        list_transaction_list_view.setAdapter(
            TransactionAdapter(listTransaction, this)
        )
    }
}