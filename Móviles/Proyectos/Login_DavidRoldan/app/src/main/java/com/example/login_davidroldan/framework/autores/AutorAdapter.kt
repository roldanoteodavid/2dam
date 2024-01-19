package com.example.login_davidroldan.framework.autores

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
import com.example.login_davidroldan.framework.main.SwipeGesture
import com.example.login_davidroldan.domain.modelo.Autor

class AutorAdapter(
    val context: Context,
    val actions: AutorActions
) : ListAdapter<Autor, AutorAdapter.ItemViewholder>(DiffCallback()) {

    interface AutorActions {
        fun onDelete(autor: Autor)
        fun onStartSelectMode(autor: Autor)
        fun itemHasClicked(autor: Autor)
        fun onClickItem(autorId: Int)
    }

    private var selectedAutores = mutableListOf<Autor>()

    fun startSelectMode() {
        selectedMode = true
        notifyDataSetChanged()
    }

    fun resetSelectMode() {
        selectedMode = false
        selectedAutores.clear()
        notifyDataSetChanged()
    }

    fun setSelectedItems(autoresSeleccionados: List<Autor>) {
        selectedAutores.clear()
        selectedAutores.addAll(autoresSeleccionados)
        notifyDataSetChanged()
    }

    private var selectedMode: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_autor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewCustomerBinding.bind(itemView)

        fun bind(item: Autor) {
            itemView.setOnClickListener {
                actions.onClickItem(item.authorId)
            }
            itemView.setOnLongClickListener {
                if (!selectedMode) {
                    selectedMode = true
                    actions.onStartSelectMode(item)
                    item.isSelected = true
                    binding.selected.isChecked = true
                    selectedAutores.add(item)
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
                            selectedAutores.add(item)
                        } else {
                            item.isSelected = false
                            itemView.setBackgroundColor(Color.TRANSPARENT)
                            selectedAutores.remove(item)
                        }
                        actions.itemHasClicked(item)
                    }
                }

                tvNombre.text = item.name
                tvId.text = item.authorId.toString()
                if (selectedMode) selected.visibility = View.VISIBLE
                else {
                    item.isSelected = false
                    selected.visibility = View.GONE
                }

                if (selectedAutores.contains(item)) {
                    itemView.setBackgroundColor(Color.GREEN)
                    binding.selected.isChecked = true
                } else {
                    itemView.setBackgroundColor(Color.TRANSPARENT)
                    binding.selected.isChecked = false
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Autor>() {
        override fun areItemsTheSame(oldItem: Autor, newItem: Autor): Boolean {
            return oldItem.authorId == newItem.authorId
        }

        override fun areContentsTheSame(oldItem: Autor, newItem: Autor): Boolean {
            return oldItem == newItem
        }
    }

    val swipeGesture = object : SwipeGesture(context) {

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    selectedAutores.remove(currentList[viewHolder.adapterPosition])
                    val deletedAutor = currentList[viewHolder.adapterPosition]
                    actions.onDelete(currentList[viewHolder.adapterPosition])
                    if (selectedMode) {
                        actions.itemHasClicked(currentList[viewHolder.adapterPosition])
                    }
                    if (currentList.contains(deletedAutor)) {
                        notifyItemChanged(viewHolder.adapterPosition)
                    }
                }
            }
        }
    }
}
