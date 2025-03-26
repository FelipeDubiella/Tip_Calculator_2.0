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
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var percentage: Int = 0

        binding.rbOptionOne.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 10
            }
        }

        binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 15
            }
        }

        binding.rbOptionThree.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 20
            }
        }

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.numOfPeople,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerNumberOfPeople.adapter = adapter

        var numOfPeopleSelected = 0
        binding.spinnerNumberOfPeople.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    numOfPeopleSelected = pos
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }

        binding.btnClear.setOnClickListener {

           clean()

        }


        binding.btnCalculate.setOnClickListener {

            val totalTableTemp = binding.billTotal.text

            if (totalTableTemp?.isEmpty() == true) {

                Snackbar.make(binding.billTotal, "Fill all the fields", Snackbar.LENGTH_LONG).show()

            } else {

                val totalTable: Float = totalTableTemp.toString().toFloat()
                val numPeople: Int = numOfPeopleSelected + 1


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
        binding.rgPercentage.clearCheck()

    }


}