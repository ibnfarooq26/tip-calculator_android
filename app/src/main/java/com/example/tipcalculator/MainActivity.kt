package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{calculateTip()}
    }
    private fun calculateTip(){
       val costInString= binding.costOfServiceEditText.text.toString()
        val cost= costInString.toDoubleOrNull()
        if(cost==null){
            binding.tipResult.text = ""
            return
        }
        val tipPercentage= when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_eighteen_percent->0.18
            R.id.option_fifteen_percent->0.15
            else -> {0.2}
        }
        var tipAmount= cost*tipPercentage
        if(binding.roundUpSwitch.isChecked){
            tipAmount= ceil(tipAmount)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tipAmount)
        binding.tipResult.text=getString(R.string.tip_amount,formattedTip)

    }
}