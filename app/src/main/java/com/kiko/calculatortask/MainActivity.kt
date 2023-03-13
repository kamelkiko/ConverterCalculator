package com.kiko.calculatortask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var binaryEt: TextInputEditText
    private lateinit var decimalEt: TextInputEditText
    private lateinit var octalEt: TextInputEditText
    private lateinit var hexEt: TextInputEditText
    private lateinit var clearBtn: MaterialButton
    private lateinit var textWatcher: TextWatcher
    private lateinit var onFocusChangeListener: View.OnFocusChangeListener
    private lateinit var converter: Converter
    private var focusId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        converter = Converter()
        initViews()
        addCallBack()
    }

    private fun initViews() {
        binaryEt = findViewById(R.id.binary)
        decimalEt = findViewById(R.id.decimal)
        octalEt = findViewById(R.id.octal)
        hexEt = findViewById(R.id.hex)
        clearBtn = findViewById(R.id.clear)
    }

    private fun addCallBack() {
        clearBtn.setOnClickListener {
            clearInputs()
        }

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.isNotEmpty() == true)
                    convertNumber(s)
                else
                    clearInputs()
            }

        }
        onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            focusId = view.id
            val focusIdClicked = findViewById<EditText>(focusId)
            if (hasFocus)
                focusIdClicked.addTextChangedListener(textWatcher)
            else
                focusIdClicked.removeTextChangedListener(textWatcher)
        }
        setOnFocused()
    }

    private fun setOnFocused() {
        binaryEt.onFocusChangeListener = onFocusChangeListener
        decimalEt.onFocusChangeListener = onFocusChangeListener
        octalEt.onFocusChangeListener = onFocusChangeListener
        hexEt.onFocusChangeListener = onFocusChangeListener
    }

    private fun convertNumber(s: Editable?) {
        converter.convert(focusId, s)
        when (focusId) {
            R.id.binary -> {
                decimalEt.setText(converter.decimal)
                octalEt.setText(converter.octal)
                hexEt.setText(converter.hex)
            }
            R.id.decimal -> {
                binaryEt.setText(converter.binary)
                octalEt.setText(converter.octal)
                hexEt.setText(converter.hex)
            }
            R.id.octal -> {
                decimalEt.setText(converter.decimal)
                binaryEt.setText(converter.binary)
                hexEt.setText(converter.hex)
            }
            R.id.hex -> {
                decimalEt.setText(converter.decimal)
                octalEt.setText(converter.octal)
                binaryEt.setText(converter.binary)
            }
        }

    }

    private fun clearInputs() {
        binaryEt.text?.clear()
        decimalEt.text?.clear()
        octalEt.text?.clear()
        hexEt.text?.clear()
    }
}