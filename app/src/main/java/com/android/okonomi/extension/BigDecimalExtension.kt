package com.android.okonomi.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.currencyToCad() : String{
    val currencyCad = DecimalFormat
        .getCurrencyInstance(Locale("en", "ca"))
    return currencyCad
        .format(this)
        .replace("$", "$ ")
        .replace("-$ ", "$ -")
}