package com.example.mygeniusgastroproyec.ui

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygeniusgastroproyec.data.RecetaEntity
import com.example.mygeniusgastroproyec.databinding.ItemRecetaBinding
import com.example.mygeniusgastroproyec.ui.home.DetalleRecetaActivity
import java.io.FileNotFoundException

class RecetaRoomAdapter(private val context: Context) :
    RecyclerView.Adapter<RecetaRoomAdapter.RecetaViewHolder>() {

    private var lista: List<RecetaEntity> = emptyList()

    inner class RecetaViewHolder(val binding: ItemRecetaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(receta: RecetaEntity) {
            binding.textNombreReceta.text = receta.nombre


            try {
                if (receta.imagenUri.isNotEmpty()) {
                    val uri = Uri.parse(receta.imagenUri)
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    binding.imageview.setImageBitmap(bitmap)
                } else {
                    binding.imageview.setImageResource(android.R.drawable.ic_menu_gallery)
                }
            } catch (e: FileNotFoundException) {
                binding.imageview.setImageResource(android.R.drawable.ic_menu_gallery)
            }

            binding.root.setOnClickListener {
                val intent = Intent(context, DetalleRecetaActivity::class.java).apply {
                    putExtra("nombre", receta.nombre)
                    putExtra("descripcion", "Receta guardada por el usuario")
                    putExtra("ingredientes", receta.ingredientes)
                    putExtra("pasos", receta.preparacion)
                    putExtra("imagenUri", receta.imagenUri)
                    putExtra("autor", receta.autor) // 👈 Se agrega el autor
                    putExtra("id", receta.id)       // 👈 Se agrega ID (para editar/eliminar)
                }
                context.startActivity(intent)
            }

            binding.favoritoButton.setImageResource(0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val binding = ItemRecetaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecetaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size

    fun actualizarLista(nuevaLista: List<RecetaEntity>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}
