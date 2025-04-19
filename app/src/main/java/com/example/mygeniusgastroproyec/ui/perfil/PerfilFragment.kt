package com.example.mygeniusgastroproyec.ui.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mygeniusgastroproyec.databinding.FragmentPerfilBinding
import com.example.mygeniusgastroproyec.utils.SessionManager

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root = binding.root

        // Cargar los datos guardados (nombre, email y descripción)
        val nombreGuardado = SessionManager.getUsuario(requireContext())
        val emailGuardado = SessionManager.getEmail(requireContext())
        val descripcionGuardada = SessionManager.getDescripcion(requireContext())

        // Mostrar en los campos
        binding.editNombre.setText(nombreGuardado)
        binding.editEmail.setText(emailGuardado)
        binding.editDescripcion.setText(descripcionGuardada)

        // Botón para guardar cambios
        binding.buttonEditarPerfil.setOnClickListener {
            val nuevoNombre = binding.editNombre.text.toString().trim()
            val nuevoEmail = binding.editEmail.text.toString().trim()
            val nuevaDesc = binding.editDescripcion.text.toString().trim()

            if (nuevoNombre.isEmpty() || nuevoEmail.isEmpty()) {
                Toast.makeText(requireContext(), "Completa al menos nombre y correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guardar en SharedPreferences
            SessionManager.saveUsuario(requireContext(), nuevoNombre)
            SessionManager.saveEmail(requireContext(), nuevoEmail)
            SessionManager.saveDescripcion(requireContext(), nuevaDesc)

            Toast.makeText(requireContext(), "Perfil actualizado con éxito", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
