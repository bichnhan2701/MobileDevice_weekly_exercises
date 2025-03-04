package com.example.thuchanh1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.editName)
        val age = findViewById<EditText>(R.id.editAge)
        val result = findViewById<TextView>(R.id.txtDisplay)
        val btn = findViewById<Button>(R.id.btnCheck)

        btn.setOnClickListener {
            val nameText = name.text.toString()
            val ageText = age.text.toString()

            if (ageText.isNotEmpty()) {
                val userAge = ageText.toInt()
                val txtResult = when {
                    userAge < 13 -> "Tre em"
                    userAge in 13..59 -> "Nguoi lon"
                    else -> "Nguoi gia"
                }
                result.text = "$nameText la $txtResult"
            }
            else {
                Toast.makeText(this, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}