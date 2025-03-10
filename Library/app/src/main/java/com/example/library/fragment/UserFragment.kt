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
import com.example.library.adapter.UserAdapter
import com.example.library.model.Library

class UserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userRecyclerView = view.findViewById<RecyclerView>(R.id.user_recycler_view)
        userRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = UserAdapter(Library.users)
        userRecyclerView.adapter = adapter
    }
}