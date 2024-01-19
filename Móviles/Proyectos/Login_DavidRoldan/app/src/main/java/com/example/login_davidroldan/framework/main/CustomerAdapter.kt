package com.example.login_davidroldan.framework.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.login_davidroldan.R
import com.example.login_davidroldan.databinding.ViewCustomerBinding
import com.example.login_davidroldan.domain.modelo.Customer


class CustomerAdapter(
    val context: Context, val actions: CustomerActions
) : ListAdapter<Customer, CustomerAdapter.ItemViewholder>(DiffCallback()) {

    interface CustomerActions {
        fun onDelete(customer: Customer)
        fun onStartSelectMode(customer: Customer)
        fun itemHasClicked(customer: Customer)
        fun onClickItem(customerId: Int)
    }

    private var selectedCustomers = mutableListOf<Customer>()

    fun startSelectMode() {
        selectedMode = true
        notifyDataSetChanged()
    }

    fun resetSelectMode() {
        selectedMode = false
        selectedCustomers.clear()
        notifyDataSetChanged()
    }

    fun setSelectedItems(customersSeleccionados: List<Customer>) {
        selectedCustomers.clear()
        selectedCustomers.addAll(customersSeleccionados)
        notifyDataSetChanged()
    }

    private var selectedMode: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_customer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewCustomerBinding.bind(itemView)

        fun bind(item: Customer) {
            itemView.setOnClickListener {
                actions.onClickItem(item.id)
            }
            itemView.setOnLongClickListener {
                if (!selectedMode) {
                    selectedMode = true
                    actions.onStartSelectMode(item)
                    item.isSelected = true
                    binding.selected.isChecked = true
                    selectedCustomers.add(item)
                    notifyItemChanged(adapterPosition)
                }
                true
            }
            with(binding) {
                selected.setOnClickListener {
                    if (selectedMode) {

                        if (binding.selected.isChecked) {
                            item.isSelected = true
                            itemView.setBackgroundColor(Color.GREEN)
                            selectedCustomers.add(item)
                        } else {
                            item.isSelected = false
                            itemView.setBackgroundColor(Color.TRANSPARENT)
                            selectedCustomers.remove(item)

                        }
                        actions.itemHasClicked(item)
                        notifyItemChanged(adapterPosition)
                    }
                }

                tvNombre.text = item.firstname
                tvId.text = item.id.toString()
                if (selectedMode) selected.visibility = View.VISIBLE
                else {
                    item.isSelected = false
                    selected.visibility = View.GONE
                }

                if (selectedCustomers.contains(item)) {
                    itemView.setBackgroundColor(Color.GREEN)
                    binding.selected.isChecked = true
                } else {
                    itemView.setBackgroundColor(Color.TRANSPARENT)
                    binding.selected.isChecked = false
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem == newItem
        }
    }

    val swipeGesture = object : SwipeGesture(context) {

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    selectedCustomers.remove(currentList[viewHolder.adapterPosition])
                    val deletedCustomer = currentList[viewHolder.adapterPosition]
                    actions.onDelete(currentList[viewHolder.adapterPosition])
                    if (selectedMode) {
                        actions.itemHasClicked(currentList[viewHolder.adapterPosition])
                    }
                    if (currentList.contains(deletedCustomer)) {
                        notifyItemChanged(viewHolder.adapterPosition)
                    }

                }
            }
        }
    }
}