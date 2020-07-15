package com.android.okonomi.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.android.okonomi.R
import com.android.okonomi.model.TypeOfTransaction

class AddTransactionDialog(
    viewGroup: ViewGroup,
    context: Context
) : TransactionDialogForm(context, viewGroup) {


    override val titleButtonPositive: String
        get() = "add"


    override fun titleBy(type: TypeOfTransaction): Int {

        return if (type == TypeOfTransaction.INCOME) {
            R.string.text_income
        } else {
            R.string.text_debt
        }
    }
    }
