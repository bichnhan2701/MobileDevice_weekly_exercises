package com.example.library.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.library.R
import com.example.library.adapter.BookAdapter
import com.example.library.model.Library

class BookFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookRecyclerView = view.findViewById<RecyclerView>(R.id.book_recycler_view)
        bookRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = BookAdapter(Library.books)
        bookRecyclerView.adapter = adapter
    }
}