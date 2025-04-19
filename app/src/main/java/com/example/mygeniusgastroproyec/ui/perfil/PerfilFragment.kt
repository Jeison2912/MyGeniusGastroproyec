package com.example.mygeniusgastroproyec.ui.perfil

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.mygeniusgastroproyec.databinding.FragmentPerfilBinding
import com.example.mygeniusgastroproyec.utils.SessionManager

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    // Lanzador de selección de imagen desde la galería
    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            SessionManager.saveImagenPerfil(requireContext(), it.toString())
            binding.imagePerfil.setImageURI(it)
        } ?: Toast.makeText(requireContext(), "No se seleccionó imagen", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root = binding.root

        // Cargar datos guardados
        val nombreGuardado = SessionManager.getUsuario(requireContext())
        val emailGuardado = SessionManager.getEmail(requireContext())
        val descripcionGuardada = SessionManager.getDescripcion(requireContext())
        val imagenUri = SessionManager.getImagenPerfil(requireContext())

        binding.editNombre.setText(nombreGuardado)
        binding.editEmail.setText(emailGuardado)
        binding.editDescripcion.setText(descripcionGuardada)

        if (imagenUri.isNotEmpty()) {
            binding.imagePerfil.setImageURI(Uri.parse(imagenUri))
        }

        // Permitir cambiar la foto al tocar la imagen
        binding.imagePerfil.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }

        // Guardar cambios de perfil
        binding.buttonEditarPerfil.setOnClickListener {
            val nuevoNombre = binding.editNombre.text.toString().trim()
            val nuevoEmail = binding.editEmail.text.toString().trim()
            val nuevaDesc = binding.editDescripcion.text.toString().trim()

            if (nuevoNombre.isEmpty() || nuevoEmail.isEmpty()) {
                Toast.makeText(requireContext(), "Completa al menos nombre y correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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
