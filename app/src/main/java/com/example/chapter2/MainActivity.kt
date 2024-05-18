package com.example.chapter2

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.chapter2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var inputNumber: Int = 0
    var bool = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        val view = binding.root
        setContentView(view)

        val inputTextView = binding.textview
        val outputTextView = binding.textview3
        val inputUnit = binding.centerInside
        val outputUnit = binding.textview2
        val snapButton = binding.snapButton


        inputTextView.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()){
                0
            } else{
                text.toString().toInt()
            }
            if(bool){
                outputTextView.text = inputNumber.times(0.01).toString()
            }else{
                outputTextView.text = inputNumber.times(100).toString()
            }
        }

        snapButton.setOnClickListener {
            bool = !bool
            if(bool){
                inputUnit.text = "cm"
                outputUnit.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            }else {
                inputUnit.text = "m"
                outputUnit.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }



    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("bool",bool)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        bool = savedInstanceState.getBoolean("cmToM")
        binding.textview2.text= if(bool) "cm" else "m"
        binding.centerInside.text = if(bool) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}