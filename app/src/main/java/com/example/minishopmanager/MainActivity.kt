package com.example.minishopmanager

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listViewProduits)
        val produits = lireProduitsDepuisJSON()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, produits)
        listView.adapter = adapter

        // Lorsqu'on clique sur un produit, on l'ajoute à la base locale
        listView.setOnItemClickListener { _, _, position, _ ->
            val produitSelectionne = produits[position]
            Toast.makeText(
                this,
                "Produit sélectionné : $produitSelectionne",
                Toast.LENGTH_SHORT,
            ).show()

            val dbHelper = DBHelper(this)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("nom", produitSelectionne)
                put("prix", (10..200).random())
            }
            db.insert("panier", null, values)
            db.close()

            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra("CATEGORY_NAME", produitSelectionne)
            }
            startActivity(intent)
        }

        val btnSuivant = findViewById<Button>(R.id.btnSuivant)
        btnSuivant.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }
    }

    private fun lireProduitsDepuisJSON(): ArrayList<String> {
        val produits = ArrayList<String>()
        try {
            val inputStream = assets.open("produits.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(jsonString)
            val jsonArray = jsonObject.getJSONArray("produits")
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                produits.add(item.getString("nom"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return produits
    }
}
