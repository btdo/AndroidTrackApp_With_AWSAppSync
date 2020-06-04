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
    ListAdapter<TrackItemModel, TodoListAdapter.ItemViewHolder>(DiffCallback) {

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

    companion object DiffCallback : DiffUtil.ItemCallback<TrackItemModel>() {
        override fun areItemsTheSame(
            oldItem: TrackItemModel,
            newItem: TrackItemModel
        ): Boolean {
            return oldItem.pin == newItem.pin
        }

        override fun areContentsTheSame(
            oldItem: TrackItemModel,
            newItem: TrackItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ItemViewHolder(
        private var binding: ItemTodoBinding, private var deleteListener: ItemDeleteListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TrackItemModel) {
            binding.item = item
            binding.itemDelete.setOnClickListener { deleteListener.onClick(item) }
            binding.executePendingBindings()
        }
    }
}

class ItemClickedListener(val clickListener: (day: TrackItemModel) -> Unit) {
    fun onClick(item: TrackItemModel) = clickListener(item)
}

class ItemDeleteListener(val clickListener: (day: TrackItemModel) -> Unit) {
    fun onClick(item: TrackItemModel) = clickListener(item)
}