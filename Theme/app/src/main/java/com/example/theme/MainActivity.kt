package com.example.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.theme.datastore.DataStoreManager
import com.example.theme.ui.screen.ThemeSelectorApp

class MainActivity : ComponentActivity() {
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        dataStoreManager = DataStoreManager(this)
        setContent {
            ThemeSelectorApp(dataStoreManager)
        }
    }
}

