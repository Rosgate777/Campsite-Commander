package com.example.imadexamination

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedViewScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvDisplayList: TextView = findViewById(R.id.tvDisplayList)
        val btnBackToBase: Button = findViewById(R.id.btnBackToBase)

        // Building the list string using a loop through the parallel arrays
        val displayBuilder = StringBuilder()

        for (i in 0 until CampData.itemNames.size) {
            displayBuilder.append("⛺ Item: ${CampData.itemNames[i]}\n")
            displayBuilder.append("📁 Category: ${CampData.itemCategories[i]}\n")
            displayBuilder.append("🔢 Qty: ${CampData.itemQuantities[i]}\n")
            displayBuilder.append("💬 Notes: ${CampData.itemComments[i]}\n")
            displayBuilder.append("--------------------------------------------------\n\n")
        }

        // Output to screen
        tvDisplayList.text = displayBuilder.toString()

        // Navigation back to Main Screen
        btnBackToBase.setOnClickListener {
            finish() // Closes this activity and returns smoothly to MainActivity
        }

    }
}