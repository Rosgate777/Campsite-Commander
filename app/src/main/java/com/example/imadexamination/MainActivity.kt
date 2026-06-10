package com.example.imadexamination

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvTotalItems: TextView
    private lateinit var etItemName: EditText
    private lateinit var etCategory: EditText
    private lateinit var etQuantity: EditText
    private lateinit var etComment: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvTotalItems = findViewById(R.id.tvTotalItems)
        etItemName = findViewById(R.id.etItemName)
        etCategory = findViewById(R.id.etCategory)
        etQuantity = findViewById(R.id.etQuantity)
        etComment = findViewById(R.id.etComment)

        val btnAddGear: Button = findViewById(R.id.btnAddGear)
        val btnViewList: Button = findViewById(R.id.btnViewList)

        // Update the display
        updateTotalItemCount()

        // Handle Add Gear Button
        btnAddGear.setOnClickListener {
            val name = etItemName.text.toString().trim()
            val category = etCategory.text.toString().trim()
            val qtyString = etQuantity.text.toString().trim()
            val comment = etComment.text.toString().trim()

            // Error handling: Ensure fields aren't blank and quantity is a real number
            if (name.isEmpty() || category.isEmpty() || qtyString.isEmpty() || comment.isEmpty()) {
                Toast.makeText(this, "Error: Please fill in all fields!", Toast.LENGTH_SHORT).show()
            } else {
                val quantity = qtyString.toIntOrNull()
                if (quantity == null || quantity <= 0) {
                    Toast.makeText(this, "Error: Quantity must be a valid number greater than 0", Toast.LENGTH_SHORT).show()
                } else {
                    // Save to parallel arrays in CampData object
                    CampData.addItem(name, category, quantity, comment)

                    // Clear inputs and show success message
                    etItemName.text.clear()
                    etCategory.text.clear()
                    etQuantity.text.clear()
                    etComment.text.clear()

                    Toast.makeText(this, "$name added successfully!", Toast.LENGTH_SHORT).show()
                    updateTotalItemCount()
                }
            }
        }

        // Handle Screen Navigation to Detailed Screen
        btnViewList.setOnClickListener {
            val intent = Intent(this, DetailedViewScreen::class.java)
            startActivity(intent)
        }
    }

    // MANDATORY REQUIREMENT: Use a loop to calculate total number of items
    private fun updateTotalItemCount() {
        var total = 0
        // Accessing the shared data object
        for (i in 0 until CampData.itemQuantities.size) {
            total += CampData.itemQuantities[i]
        }
        tvTotalItems.text = "Total Items Packed: $total"
    }

    override fun onResume() {
        super.onResume()
        // Keeps total updated if changes happen
        updateTotalItemCount()
    }
}