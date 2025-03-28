package com.felipedubiella.tip_calculator_20

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.felipedubiella.tip_calculator_20.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnClear.setOnClickListener {

           clean()

        }


        binding.btnCalculate.setOnClickListener {

            val totalTableTemp = binding.billTotal.text
            val numPeopleTemp = binding.edtNumPeople.text
            val percentageTemp = binding.edtPercentage.text

            if (totalTableTemp?.isEmpty() == true ||
                numPeopleTemp?.isEmpty() == true ||
                percentageTemp?.isEmpty() == true) {

                Snackbar.make(binding.billTotal, "Fill all the fields", Snackbar.LENGTH_LONG).show()

            } else {

                val totalTable: Float = totalTableTemp.toString().toFloat()
                val numPeople: Int = numPeopleTemp.toString().toInt()
                val percentage: Float = percentageTemp.toString().toFloat()


                val totalTemp = totalTable / numPeople
                val tips = totalTemp * percentage / 100
                val totalWithTips = totalTemp + tips

                val intent = Intent(this, summaryActivity::class.java)
                intent.putExtra("totalTable", totalTable)
                intent.putExtra("numPeople", numPeople)
                intent.putExtra("totalWithTips", totalWithTips)
                intent.putExtra("percentage", percentage)
                clean()
                startActivity(intent)
            }


        }


    }

    private fun clean(){

        binding.billTotal.text?.clear()
        binding.edtNumPeople.text?.clear()
        binding.edtPercentage.text?.clear()

    }


}