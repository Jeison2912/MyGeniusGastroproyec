package com.example.mygeniusgastroproyec.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mygeniusgastroproyec.R
import com.example.mygeniusgastroproyec.data.FavoritosManager
import com.example.mygeniusgastroproyec.ui.RecetaAdapter
import com.example.mygeniusgastroproyec.ui.home.Receta

class FavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recetaAdapter: RecetaAdapter
    private lateinit var textoVacio: TextView
    private val recetasFavoritas: MutableList<Receta> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite, container, false)

        recyclerView = rootView.findViewById(R.id.recyclerViewFavoritos)
        textoVacio = rootView.findViewById(R.id.textEmptyFavorites)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val favoritosManager = FavoritosManager(requireContext())
        recetasFavoritas.addAll(favoritosManager.obtenerFavoritos())

        mostrarMensajeSiListaVacia()

        recetaAdapter = RecetaAdapter(
            requireContext(),
            recetasFavoritas,
            modoFavoritos = true
        ) { recetaEliminada ->
            recetasFavoritas.remove(recetaEliminada)
            recetaAdapter.notifyDataSetChanged()
            mostrarMensajeSiListaVacia()
        }

        recyclerView.adapter = recetaAdapter

        return rootView
    }

    private fun mostrarMensajeSiListaVacia() {
        textoVacio.visibility = if (recetasFavoritas.isEmpty()) View.VISIBLE else View.GONE
    }
}
