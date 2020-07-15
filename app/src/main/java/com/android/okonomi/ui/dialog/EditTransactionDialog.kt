package com.android.okonomi.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.android.okonomi.R
import com.android.okonomi.delegate.TransactionDelegate
import com.android.okonomi.extension.formatToEnCa
import com.android.okonomi.model.Transaction
import com.android.okonomi.model.TypeOfTransaction

class EditTransactionDialog(
    viewGroup: ViewGroup,
    private val context: Context
) : TransactionDialogForm(context, viewGroup) {


    override val titleButtonPositive: String
        get() = "edit"

    override fun titleBy(type: TypeOfTransaction): Int {
        return if (type == TypeOfTransaction.INCOME) {
            R.string.text_income
        } else {
            R.string.text_debt
        }
    }


    fun setUpDialog(transaction: Transaction, transactionDelegate: TransactionDelegate) {
        val type = transaction.typeOfTransaction

        super.setUpDialog(type, transactionDelegate)

        startsField(transaction)
    }

    private fun startsField(
        transaction: Transaction
    ) {
        val type = transaction.typeOfTransaction
        startsAmountField(transaction)
        startsDateField(transaction)
        startsCategoryField(type, transaction)
    }

    private fun startsCategoryField(
        type: TypeOfTransaction,
        transaction: Transaction
    ) {
        val selectedCategories = context.resources.getStringArray(categoriesBy(type))
        val indexOfCategory = selectedCategories.indexOf(transaction.categoryOfTransaction)
        categoryField.setSelection(indexOfCategory, true)
    }

    private fun startsDateField(transaction: Transaction) {
        dateField.setText(transaction.date.formatToEnCa())
    }

    private fun startsAmountField(transaction: Transaction) {
        amountField.setText(transaction.amount.toString())
    }


}