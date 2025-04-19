package com.example.mygeniusgastroproyec.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygeniusgastroproyec.data.RecetaEntity
import com.example.mygeniusgastroproyec.databinding.FragmentHomeBinding
import com.example.mygeniusgastroproyec.ui.RecetaRoomAdapter
import com.example.mygeniusgastroproyec.viewmodel.RecetaViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val recetaViewModel: RecetaViewModel by viewModels()
    private lateinit var adapter: RecetaRoomAdapter
    private var listaRecetas: List<RecetaEntity> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecetaRoomAdapter(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Observar recetas desde Room
        recetaViewModel.todasLasRecetas.observe(viewLifecycleOwner) { recetas ->
            listaRecetas = recetas
            adapter.actualizarLista(recetas)
        }

        // Buscar recetas
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val texto = newText.orEmpty().lowercase()
                val filtradas = listaRecetas.filter {
                    it.nombre.lowercase().contains(texto)
                }
                adapter.actualizarLista(filtradas)
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

