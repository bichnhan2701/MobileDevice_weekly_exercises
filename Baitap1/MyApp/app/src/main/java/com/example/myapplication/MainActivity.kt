package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Gan du lieu
        val profileName = findViewById<TextView>(R.id.profileName)
        val profileAddress = findViewById<TextView>(R.id.profileAddress)
        val profileImage = findViewById<CircleImageView>(R.id.profileImage)

        profileName.text = "Nguyen Thi Bich Nhan"
        profileAddress.text = "Tp. Ho Chi Minh"
        profileImage.setImageResource(R.drawable.meoloading)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}