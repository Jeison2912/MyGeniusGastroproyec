package com.example.mygeniusgastroproyec.ui.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mygeniusgastroproyec.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // EditText para Nombre, Email y Descripción
        val editNombre: EditText = binding.editNombre
        val editEmail: EditText = binding.editEmail
        val editDescripcion: EditText = binding.editDescripcion

        // Botón para editar perfil
        val buttonEditarPerfil: Button = binding.buttonEditarPerfil

        // Acción del botón "Editar Perfil"
        buttonEditarPerfil.setOnClickListener {
            // Cambiar los EditText a editables
            editNombre.isFocusableInTouchMode = true
            editEmail.isFocusableInTouchMode = true
            editDescripcion.isFocusableInTouchMode = true

            // Mostrar un mensaje para indicar que la edición está habilitada
            Toast.makeText(requireContext(), "Ahora puedes editar tu perfil", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
