package com.example.library

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.library.fragment.BookFragment
import com.example.library.fragment.HomeFragment
import com.example.library.fragment.UserFragment
import com.example.library.model.Library
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Library.initializeData()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.home_icon -> loadFragment(HomeFragment())
                R.id.folder_icon -> loadFragment(BookFragment())
                R.id.user_icon -> loadFragment(UserFragment())
            }
            true
        }

        loadFragment(HomeFragment())

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}