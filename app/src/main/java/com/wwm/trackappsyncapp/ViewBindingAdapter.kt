package com.wwm.trackappsyncapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("list")
fun bindTodoList(
    recyclerView: RecyclerView,
    data: List<TrackItemModel>?
) {
    val adapter = recyclerView.adapter as TodoListAdapter
    adapter.submitList(data)
}