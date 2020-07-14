package com.android.okonomi.model

import java.math.BigDecimal

class Total(private val listTransaction: List<Transaction>) {

    fun income(): BigDecimal {
        var totalIncome = BigDecimal.ZERO
        for (transaction in listTransaction) {
            if (transaction.typeOfTransaction == TypeOfTransaction.INCOME) {
                totalIncome = totalIncome.plus(transaction.amount)
            }
        }
        return totalIncome
    }

    fun debt(): BigDecimal{
        var totalDebt = BigDecimal.ZERO
        for (transaction in listTransaction) {
            if (transaction.typeOfTransaction == TypeOfTransaction.DEBT) {
                totalDebt = totalDebt.plus(transaction.amount)
            }
        }
        return totalDebt
    }

    fun total(): BigDecimal{
        return income().subtract(debt())
    }

}