package com.felipedubiella.tip_calculator_20

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.felipedubiella.tip_calculator_20.databinding.ActivityMainBinding
import com.felipedubiella.tip_calculator_20.databinding.ActivitySummaryBinding

private lateinit var binding: ActivitySummaryBinding

class summaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null ){

                val totalTable = bundle?.getFloat( "totalTable")
                val numPeople = bundle?.getInt( "numPeople")
                val totalWithTips = bundle?.getFloat( "totalWithTips")
                val percentage = bundle?.getFloat( "percentage")

            binding.tvTotalTable.text = totalTable.toString()
            binding.tvNumPeople.text = numPeople.toString()
            binding.tvTotalWithTips.text = totalWithTips.toString()
            binding.tvPercentage.text = percentage.toString() + "%"

            binding.btnNewCalc.setOnClickListener {
                finish()
            }



    }


}
}