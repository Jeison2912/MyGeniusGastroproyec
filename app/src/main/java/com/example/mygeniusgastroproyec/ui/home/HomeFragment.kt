package com.example.mygeniusgastroproyec.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygeniusgastroproyec.R
import com.example.mygeniusgastroproyec.data.RecetaRepository
import com.example.mygeniusgastroproyec.databinding.FragmentHomeBinding
import com.example.mygeniusgastroproyec.ui.RecetaAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RecetaAdapter
    private lateinit var listaCompleta: MutableList<Receta>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa la lista combinada
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

        // Agrega recetas creadas por el usuario
        listaCompleta.addAll(RecetaRepository.listaRecetas)

        // Configurar el RecyclerView
        adapter = RecetaAdapter(requireContext(), listaCompleta)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val texto = newText.orEmpty().lowercase()
                val listaFiltrada = listaCompleta.filter {
                    it.nombre.lowercase().contains(texto)
                }.toMutableList()
                adapter.updateList(listaFiltrada)
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()

        // Recarga recetas al volver al fragmento
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
        ).apply {
            addAll(RecetaRepository.listaRecetas)
        }

        adapter.updateList(listaCompleta)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
