package com.example.library.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.library.R
import com.example.library.model.Book

class BookTableAdapter(private val books: List<Book>) : RecyclerView.Adapter<BookTableAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvStatus: TextView = view.findViewById(R.id.tv_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_table, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.tvTitle.text = book.title
        holder.tvStatus.text = if (book.isBorrowed) "Đã mượn bởi: ${book.borrowBy?.name}" else "Có sẵn"
    }

    override fun getItemCount(): Int = books.size
}