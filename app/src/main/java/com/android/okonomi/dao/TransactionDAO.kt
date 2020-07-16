package com.android.okonomi.dao

import com.android.okonomi.model.Transaction

class TransactionDAO {

     val listTransaction: MutableList<Transaction> = mutableListOf()



    fun addTransaction(transaction: Transaction){
        listTransaction.add(transaction)
    }

    fun editTransaction(transaction: Transaction, position: Int){
        listTransaction[position] = transaction
    }

    fun deleteTransaction(position: Int){
        listTransaction.removeAt(position)
    }

}