package com.example.library.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    var isBorrowed: Boolean = false,
    var borrowBy: User? = null
)