package com.example.minishopmanager

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val tvCategoryTitle = findViewById<TextView>(R.id.tvCategoryTitle)
        val listViewSubProducts = findViewById<ListView>(R.id.listViewSubProducts)
        val btnRetour = findViewById<Button>(R.id.btnRetour)

        // Get the category name passed from MainActivity
        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: "Produit"
        tvCategoryTitle.text = categoryName

        // Define sub-items based on the selected category
        val subItems = when (categoryName) {
            "Téléphone" -> arrayOf("iPhone 15", "Samsung Galaxy S24", "Xiaomi Redmi Note 13", "Google Pixel 8")
            "Casque Bluetooth" -> arrayOf("Sony WH-1000XM5", "Apple AirPods Pro", "JBL Tune 720BT", "Bose QuietComfort")
            "Montre connectée" -> arrayOf("Apple Watch Series 9", "Samsung Galaxy Watch 6", "Garmin Forerunner", "Xiaomi Smart Band 8")
            "Chargeur USB" -> arrayOf("Chargeur Anker 20W", "Câble USB-C Rapide", "Chargeur Sans Fil Qi", "Multiprise USB Murale")
            else -> arrayOf("Modèle 1", "Modèle 2", "Modèle 3")
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, subItems)
        listViewSubProducts.adapter = adapter

        // Handle clicks on sub-items to show Toast with the selected product name
        listViewSubProducts.setOnItemClickListener { _, _, position, _ ->
            val subItem = subItems[position]
            Toast.makeText(
                this,
                "Produit sélectionné : $subItem",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnRetour.setOnClickListener {
            finish()
        }
    }
}