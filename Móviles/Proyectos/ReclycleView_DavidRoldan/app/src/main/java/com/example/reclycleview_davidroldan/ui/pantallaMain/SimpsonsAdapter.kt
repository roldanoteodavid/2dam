package com.example.reclycleview_davidroldan.ui.pantallaMain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.reclycleview_davidroldan.R
import com.example.reclycleview_davidroldan.data.model.Simpsonjson
import com.example.reclycleview_davidroldan.databinding.ItemPersonajeBinding
import com.example.reclycleview_davidroldan.ui.Constantes

class PersonasAdapter(
    private var simpsons: List<Simpsonjson>,
    private val onClickBoton: (Int) -> Unit
) : RecyclerView.Adapter<PersonasViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonasViewHolder(layoutInflater.inflate(R.layout.item_personaje, parent, false))
    }


    override fun onBindViewHolder(holder: PersonasViewHolder, position: Int) {
        holder.render(simpsons[position], onClickBoton)
    }

    override fun getItemCount(): Int = simpsons.size


    fun cambiarLista(lista: List<Simpsonjson>) {
        simpsons = lista
        notifyDataSetChanged()
    }


}

class PersonasViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemPersonajeBinding.bind(view)

    fun render(
        simpson: Simpsonjson,
        onClickBoton: (Int) -> Unit
    ) {

        with(binding) {
            imageView.load(Constantes.IMG_HOMER)
            tvId.text = simpson.id.toString()
            tvNombre.text = simpson.name
            button2.setOnClickListener {
                onClickBoton(simpson.id);
            }
        }


    }
}
