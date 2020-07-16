package com.android.okonomi.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.okonomi.R
import com.android.okonomi.delegate.TransactionDelegate
import com.android.okonomi.extension.convertToCalendar
import com.android.okonomi.extension.formatToEnCa
import com.android.okonomi.model.Transaction
import com.android.okonomi.model.TypeOfTransaction
import kotlinx.android.synthetic.main.form_transaction.view.*
import java.math.BigDecimal
import java.util.*

abstract class TransactionDialogForm(
    private val context: Context,
    private val viewGroup: ViewGroup
) {
    private val createdView = createsLayout()
    protected val amountField = createdView.form_new_transaction_amount
    protected val categoryField = createdView.form_options_category
    protected val dateField = createdView.form_new_transaction_date
    protected abstract val titleButtonPositive: String

    fun setUpDialog(type: TypeOfTransaction, delegate: (transaction : Transaction) -> Unit) {
        setUpDateField()
        setUpCategoryField(type)
        setUpTransactionForm(type, delegate)
    }

    private fun setUpTransactionForm(
        type: TypeOfTransaction,
        delegate: (transaction: Transaction) -> Unit
    ) {

        val title = titleBy(type)

        AlertDialog.Builder(context)
            .setTitle(title)
            .setView(createdView)
            .setPositiveButton(titleButtonPositive) { _, _ ->
                val amountToString = amountField.text.toString()
                val dateToString = dateField.text.toString()
                val categoryToString = categoryField.selectedItem.toString()

                val amount = convertAmountField(amountToString)

                val date = dateToString.convertToCalendar()

                val transactionCreated = Transaction(
                    typeOfTransaction = type,
                    amount = amount,
                    date = date,
                    categoryOfTransaction = categoryToString
                )

                delegate(transactionCreated)

            }
            .setNegativeButton("cancel", null)
            .show()
    }

    protected abstract fun titleBy(type: TypeOfTransaction): Int


    private fun convertAmountField(amountToString: String): BigDecimal {
        return try {
            BigDecimal(amountToString)
        } catch (exception: NumberFormatException) {
            Toast.makeText(
                context,
                "you need to input an amount : )",
                Toast.LENGTH_LONG
            )
                .show()
            BigDecimal.ZERO
        }
    }

    private fun setUpCategoryField(type: TypeOfTransaction) {

        val categories = categoriesBy(type)

        val adapter = ArrayAdapter
            .createFromResource(
                context,
                categories,
                android.R.layout.simple_spinner_dropdown_item
            )

        categoryField.adapter = adapter
    }

    protected fun categoriesBy(type: TypeOfTransaction): Int {
        return if (type == TypeOfTransaction.INCOME) {
            R.array.category_of_incomes
        } else {
            R.array.category_of_debts
        }
    }

    private fun setUpDateField() {
        val today = Calendar.getInstance()

        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        dateField.setText(today.formatToEnCa())
        dateField.setOnClickListener {
            DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, day)
                    dateField
                        .setText(selectedDate.formatToEnCa())
                }
                , year, month, day)
                .show()
        }
    }

    private fun createsLayout(): View {
        return LayoutInflater.from(context)
            .inflate(
                R.layout.form_transaction,
                viewGroup,
                false
            )
    }
}