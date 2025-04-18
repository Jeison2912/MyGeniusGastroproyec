package com.example.mygeniusgastroproyec.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygeniusgastroproyec.R
import com.example.mygeniusgastroproyec.databinding.FragmentHomeBinding
import com.example.mygeniusgastroproyec.ui.RecetaAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RecetaAdapter
    private lateinit var listaCompleta: MutableList<Receta> // Cambio aquí para que sea MutableList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cambié listOf por mutableListOf
        listaCompleta = mutableListOf(
            Receta(
                nombre = "Salmon Toscana",
                descripcion = "Deliciosa receta de salmon con salsa Toscana.",
                imagenResId = R.drawable.salmon_toscana,
                ingredientes = "Salmon, Ajo, Aceite, Limón",
                pasos = "1. Cocinar el salmon. 2. Preparar la salsa Toscana. 3. Servir."
            ),
            Receta(
                nombre = "Ensalada de Frutas",
                descripcion = "Refrescante ensalada de frutas.",
                imagenResId = R.drawable.fav1,
                ingredientes = "Fresas, Manzanas, Uvas, Naranjas",
                pasos = "1. Cortar las frutas. 2. Mezclar en un bol. 3. Servir."
            ),
            Receta(
                nombre = "Hamburguesa",
                descripcion = "Receta para hacer una deliciosa hamburguesa.",
                imagenResId = R.drawable.burger1,
                ingredientes = "Carne molida, Pan de hamburguesa, Lechuga, Tomate, Queso",
                pasos = "1. Cocinar la carne. 2. Montar la hamburguesa. 3. Servir."
            )
        )

        // Configurar el RecyclerView
        adapter = RecetaAdapter(requireContext(), listaCompleta) // Aquí paso la lista mutable
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Configurar el SearchView
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val texto = newText.orEmpty().lowercase()
                val listaFiltrada = listaCompleta.filter {
                    it.nombre.lowercase().contains(texto) // Filtra por nombre de receta
                }.toMutableList() // Convierte la lista filtrada a MutableList
                adapter.updateList(listaFiltrada) // Actualiza el adaptador con las recetas filtradas
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
