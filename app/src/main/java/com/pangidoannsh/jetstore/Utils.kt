package com.pangidoannsh.jetstore

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(value: Long): String {
    val localeID = Locale("id", "ID")
    val format = DecimalFormat.getCurrencyInstance(localeID) as DecimalFormat

    // Mengatur jumlah angka desimal menjadi 0
    format.maximumFractionDigits = 0

    return format.format(value)
}