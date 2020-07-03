package com.android.okonomi.model

import java.math.BigDecimal
import java.util.Calendar

class Transaction (val amount: BigDecimal,
                   val categoryOfTransaction: String = "undefined",
                   val typeOfTransaction: TypeOfTransaction,
                   val date: Calendar = Calendar.getInstance())