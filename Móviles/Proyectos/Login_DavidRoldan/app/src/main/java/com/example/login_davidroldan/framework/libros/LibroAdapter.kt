package com.example.login_davidroldan.framework.libros

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.login_davidroldan.R
import com.example.login_davidroldan.databinding.ViewLibroBinding
import com.example.login_davidroldan.domain.modelo.Libro
import com.example.login_davidroldan.framework.main.SwipeGesture

class LibroAdapter(
    val context: Context,
    val actions: LibroActions
) : ListAdapter<Libro, LibroAdapter.ViewHolder>(DiffCallback()) {

    fun interface LibroActions {
        fun onDelete(libro: Libro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_libro, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ViewLibroBinding.bind(itemView)

        fun bind(item: Libro) {
            binding.tvId.text = item.bookId.toString()
            binding.tvTitulo.text = item.title
            binding.tvFecha.text = item.publicationYear
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Libro>() {
        override fun areItemsTheSame(oldItem: Libro, newItem: Libro): Boolean {
            return oldItem.bookId == newItem.bookId
        }

        override fun areContentsTheSame(oldItem: Libro, newItem: Libro): Boolean {
            return oldItem == newItem
        }
    }

    val swipeGesture = object : SwipeGesture(context) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    val deletedLibro = getItem(viewHolder.adapterPosition)
                    actions.onDelete(deletedLibro)
                    currentList.toMutableList().remove(deletedLibro)
                    notifyItemRemoved(viewHolder.adapterPosition)
                }
            }
        }
    }
}