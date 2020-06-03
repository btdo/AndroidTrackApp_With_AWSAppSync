package com.wwm.trackappsyncapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wwm.trackappsyncapp.databinding.ItemTodoBinding

class TodoListAdapter(
    private var mClickListener: ItemClickedListener,
    private var itemDeleteListener: ItemDeleteListener
) :
    ListAdapter<TrackItem, TodoListAdapter.ItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context)), itemDeleteListener
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val dayForecast = getItem(position)
        holder.bind(dayForecast)
        holder.itemView.setOnClickListener {
            mClickListener.onClick(dayForecast)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TrackItem>() {
        override fun areItemsTheSame(
            oldItem: TrackItem,
            newItem: TrackItem
        ): Boolean {
            return oldItem.pin == newItem.pin
        }

        override fun areContentsTheSame(
            oldItem: TrackItem,
            newItem: TrackItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ItemViewHolder(
        private var binding: ItemTodoBinding, private var deleteListener: ItemDeleteListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TrackItem) {
            binding.item = item
            binding.itemDelete.setOnClickListener { deleteListener.onClick(item) }
            binding.executePendingBindings()
        }
    }
}

class ItemClickedListener(val clickListener: (day: TrackItem) -> Unit) {
    fun onClick(item: TrackItem) = clickListener(item)
}

class ItemDeleteListener(val clickListener: (day: TrackItem) -> Unit) {
    fun onClick(item: TrackItem) = clickListener(item)
}