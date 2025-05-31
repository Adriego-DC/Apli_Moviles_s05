package com.example.clinica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EspecialidadAdapter(
    private val lista: List<Especialidad>,
    private val onItemClick: (Especialidad) -> Unit
) : RecyclerView.Adapter<EspecialidadAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_especialidad, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val especialidad = lista[position]
        holder.tvTitulo.text = especialidad.titulo

        holder.itemView.setOnClickListener {
            onItemClick(especialidad)
        }
    }

    override fun getItemCount(): Int = lista.size
}
