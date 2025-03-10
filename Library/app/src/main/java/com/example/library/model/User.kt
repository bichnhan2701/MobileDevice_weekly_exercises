package com.example.library.model

data class User (
    val id: Int,
    val name: String,
    var listBookBorrowed: MutableList<Book> = mutableListOf()
)