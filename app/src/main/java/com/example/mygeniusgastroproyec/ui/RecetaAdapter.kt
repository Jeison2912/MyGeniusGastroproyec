package com.example.mygeniusgastroproyec.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mygeniusgastroproyec.R
import com.example.mygeniusgastroproyec.data.FavoritosManager
import com.example.mygeniusgastroproyec.databinding.ItemRecetaBinding
import com.example.mygeniusgastroproyec.ui.home.DetalleRecetaActivity
import com.example.mygeniusgastroproyec.ui.home.Receta

class RecetaAdapter(
    private val context: Context,
    private val recetas: MutableList<Receta>,
    private val modoFavoritos: Boolean = false,
    private val onFavoritoEliminado: ((Receta) -> Unit)? = null
) : RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    private val favoritosManager = FavoritosManager(context)

    inner class RecetaViewHolder(private val binding: ItemRecetaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(receta: Receta) {
            binding.textNombreReceta.text = receta.nombre

            try {
                if (receta.imagenResId != 0) {
                    binding.imageview.setImageResource(receta.imagenResId)
                } else {
                    binding.imageview.setImageResource(R.drawable.fav1)
                }
            } catch (e: Exception) {
                binding.imageview.setImageResource(R.drawable.fav1)
            }

            binding.root.setOnClickListener {
                val intent = Intent(context, DetalleRecetaActivity::class.java).apply {
                    putExtra("nombre", receta.nombre)
                    putExtra("descripcion", receta.descripcion)
                    putExtra("ingredientes", receta.ingredientes)
                    putExtra("pasos", receta.pasos)
                    putExtra("imagenResId", receta.imagenResId)
                }
                context.startActivity(intent)
            }

            // Cambiar ícono y acción según el modo
            if (modoFavoritos) {
                binding.favoritoButton.setImageResource(R.drawable.eliminar)
                binding.favoritoButton.setOnClickListener {
                    favoritosManager.eliminarFavorito(receta)
                    Toast.makeText(context, "${receta.nombre} eliminado de favoritos", Toast.LENGTH_SHORT).show()
                    onFavoritoEliminado?.invoke(receta)
                }
            } else {
                val yaEsFavorito = favoritosManager.obtenerFavoritos().any { it.nombre == receta.nombre }
                val icon = if (yaEsFavorito) R.drawable.lleno else R.drawable.vacio
                binding.favoritoButton.setImageResource(icon)

                binding.favoritoButton.setOnClickListener {
                    if (!yaEsFavorito) {
                        favoritosManager.guardarFavorito(receta)
                        Toast.makeText(context, "${receta.nombre} añadido a tus favoritos", Toast.LENGTH_SHORT).show()
                        binding.favoritoButton.setImageResource(R.drawable.lleno)
                    } else {
                        Toast.makeText(context, "${receta.nombre} ya está en tus favoritos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val binding = ItemRecetaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecetaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        holder.bind(recetas[position])
    }

    override fun getItemCount(): Int = recetas.size

    fun updateList(nuevaLista: List<Receta>) {
        recetas.clear()
        recetas.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}
