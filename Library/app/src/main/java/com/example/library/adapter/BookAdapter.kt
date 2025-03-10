package com.example.library.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.library.R
import com.example.library.model.Book
import com.example.library.model.Library

class BookAdapter(private val books: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tv_book_title)
        val authorTextView: TextView = itemView.findViewById(R.id.tv_book_author)
        var statusTextView: TextView = itemView.findViewById(R.id.tv_book_status)
        var borrowButton: Button = itemView.findViewById(R.id.btn_borrow_book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.titleTextView.text = book.title
        holder.authorTextView.text = book.author

        if (book.isBorrowed) {
            holder.statusTextView.text = "Đã mượn bởi ${book.borrowBy?.name}"
            holder.borrowButton.text = "Trả sách"
        } else {
            holder.statusTextView.text = "Có sẵn"
            holder.borrowButton.text = "Mượn sách"
        }

        holder.borrowButton.setOnClickListener {
            if (!book.isBorrowed) {
                showUserSelectionDialog(holder.itemView.context, book)
            } else {
                book.borrowBy?.let { user ->
                    Library.returnBook(book, user)
                    notifyDataSetChanged()
                    Toast.makeText(holder.itemView.context, "${user.name} đã trả sách ${book.title}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showUserSelectionDialog(context: Context, book: Book) {
        val users = Library.users
        val userNames = users.map {it.name }.toTypedArray()

        AlertDialog.Builder(context)
            .setTitle("Chọn người mượn")
            .setItems(userNames) { _, which ->
                val selectedUser = users[which]
                Library.borrowBook(book, selectedUser)
                notifyDataSetChanged()
                Toast.makeText(context, "${selectedUser.name} đã mượn sách ${book.title})", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Hủy", null)
            .show()
    }

    override fun getItemCount(): Int = books.size
}