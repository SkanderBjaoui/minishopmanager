package com.example.minishopmanager

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Setup product ListView and ArrayAdapter from string-array
        val listViewProduits = findViewById<ListView>(R.id.listViewProduits)
        val produits = resources.getStringArray(R.array.produits)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, produits)
        listViewProduits.adapter = adapter

        // 2. Handle item clicks on the product list
        listViewProduits.setOnItemClickListener { _, _, position, _ ->
            val produitSelectionne = produits[position]
            Toast.makeText(
                this,
                "Produit sélectionné : $produitSelectionne",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra("CATEGORY_NAME", produitSelectionne)
            }
            startActivity(intent)
        }

        // 3. Existing navigation button
        val btnSuivant = findViewById<Button>(R.id.btnSuivant)
        btnSuivant.setOnClickListener {
            Toast.makeText(
                this,
                "Bouton cliqué !",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }
    }
}