package com.android.okonomi.model

import java.math.BigDecimal

class Total(private val listTransaction: List<Transaction>) {

    val income: BigDecimal
        get() = sumBy(TypeOfTransaction.INCOME)

    val debt: BigDecimal
        get() = sumBy(TypeOfTransaction.DEBT)

    val total: BigDecimal
        get() = income.subtract(debt)

    private fun sumBy(type: TypeOfTransaction): BigDecimal{
        val sumTransactions = listTransaction
            .filter { it.typeOfTransaction == type }
            .sumByDouble { it.amount.toDouble() }
        return BigDecimal(sumTransactions)
    }


}
