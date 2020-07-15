package com.android.okonomi.delegate

import com.android.okonomi.model.Transaction

interface TransactionDelegate {

    fun delegate(transaction: Transaction)
}