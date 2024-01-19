package com.example.login_davidroldan.framework.orders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.login_davidroldan.R
import com.example.login_davidroldan.databinding.ViewOrderBinding
import com.example.login_davidroldan.domain.modelo.Order
import com.example.login_davidroldan.framework.main.SwipeGesture

class OrderAdapter(
    val context: Context,
    val actions: OrderActions
) : ListAdapter<Order, OrderAdapter.ViewHolder>(DiffCallback()) {

    fun interface OrderActions {
        fun onDelete(order: Order)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_order, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ViewOrderBinding.bind(itemView)

        fun bind(item: Order) {
            binding.tvId.text = item.id.toString()
            binding.tvMesa.text = item.tableId.toString()
            binding.tvFecha.text = item.date.toString()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }
    }

    val swipeGesture = object : SwipeGesture(context) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    val deletedOrder = getItem(viewHolder.adapterPosition)
                    actions.onDelete(deletedOrder)
                    currentList.toMutableList().remove(deletedOrder)
                    notifyItemRemoved(viewHolder.adapterPosition)
                }
            }
        }
    }
}