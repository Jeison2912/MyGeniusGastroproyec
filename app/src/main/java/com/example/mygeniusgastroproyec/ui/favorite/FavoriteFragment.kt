package com.example.mygeniusgastroproyec.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val recetasFavoritas: MutableList<Receta> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el layout del fragmento
        val rootView = inflater.inflate(R.layout.fragment_favorite, container, false)

        // Inicializamos el RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewFavoritos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Cargamos las recetas favoritas desde FavoritosManager
        val favoritosManager = FavoritosManager(requireContext())
        val recetas = favoritosManager.obtenerFavoritos()

        // Configuramos el RecyclerView con el adapter
        recetaAdapter = RecetaAdapter(requireContext(), recetas)
        recyclerView.adapter = recetaAdapter

        return rootView
    }
}
