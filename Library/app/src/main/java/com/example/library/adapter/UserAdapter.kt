package com.example.library.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.library.R
import com.example.library.model.User

class UserAdapter(private val users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tv_user_name)
        var borrowedBooksTextView: TextView = itemView.findViewById(R.id.tv_book_borrowed_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.nameTextView.text = user.name

        val borrowedBooksText = if (user.listBookBorrowed.isEmpty()) "Chưa mượn sách nào"
        else "Đã mượn: " + user.listBookBorrowed.joinToString(", ") {it.title}

        holder.borrowedBooksTextView.text = borrowedBooksText
    }

    override fun getItemCount(): Int = users.size
}