package com.example.appwithroomdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModelProvider
import com.example.appwithroomdatabase.ui.screen.TodoScreen
import com.example.appwithroomdatabase.ui.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: TodoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        setContent {
            MaterialTheme {
                TodoScreen(viewModel)
            }
        }
    }
}
