package com.kiko.calculatortask

import android.text.Editable

class Converter {
    lateinit var binary: String
        private set
    lateinit var decimal: String
        private set
    lateinit var octal: String
        private set
    lateinit var hex: String
        private set

    fun convert(focusId: Int, s: Editable?) {
        when (focusId) {
            R.id.binary -> convertFromBinary(s)
            R.id.decimal -> convertFromDecimal(s)
            R.id.octal -> convertFromOctal(s)
            R.id.hex -> convertFromHex(s)
        }
    }

    private fun convertFromBinary(s: Editable?) {
        decimal = s.toString().toInt(2).toString()
        octal = decimal.toInt().toString(8)
        hex = s.toString().toLong(2).toString(16)
    }

    private fun convertFromDecimal(s: Editable?) {
        if (s.toString().isNotEmpty()) {
            binary = s.toString().toInt().toString(2)
            octal = s.toString().toInt().toString(8)
            hex = s.toString().toInt().toString(16)
        }
    }

    private fun convertFromOctal(s: Editable?) {
        if (s.toString().isNotEmpty()) {
            decimal = s.toString().toInt(8).toString()
            binary = decimal.toInt().toString(2)
            hex = s.toString().toInt(8).toString(16)
        }
    }

    private fun convertFromHex(s: Editable?) {
        if (s.toString().isNotEmpty()) {
            binary = s.toString().toLong(16).toString(2)
            decimal = s.toString().toInt(16).toString()
            octal = s.toString().toInt(16).toString(8)
        }

    }
}