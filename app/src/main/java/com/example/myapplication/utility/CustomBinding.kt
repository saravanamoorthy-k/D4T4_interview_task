package com.example.myapplication.utility

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.view.adapter.AddedTaskListListAdapter

object CustomBinding {

    @BindingAdapter("rvAdapter")
    @JvmStatic
    fun recyclerViewAdapter(recyclerView: RecyclerView, adapter: Any?) {
        when (adapter) {
            is AddedTaskListListAdapter -> {
                recyclerView.adapter = adapter as RecyclerView.Adapter<*>?
            }
        }
    }

}