package com.example.mygeniusgastroproyec.ui.receta

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.mygeniusgastroproyec.R
import com.example.mygeniusgastroproyec.data.RecetaRepository
import com.example.mygeniusgastroproyec.ui.home.Receta

class AgregarFragment : Fragment() {

    private lateinit var editNombre: EditText
    private lateinit var editIngredientes: EditText
    private lateinit var editPreparacion: EditText
    private lateinit var btnGuardar: Button
    private lateinit var imageView: ImageView

    private var imagenUri: Uri? = null

    private val imagePicker = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            imagenUri = uri
            try {
                val inputStream = requireContext().contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageView.setImageBitmap(bitmap)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error al cargar imagen", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_agregar, container, false)

        editNombre = view.findViewById(R.id.editNombreReceta)
        editIngredientes = view.findViewById(R.id.editIngredientes)
        editPreparacion = view.findViewById(R.id.editPreparacion)
        btnGuardar = view.findViewById(R.id.btnGuardarReceta)
        imageView = view.findViewById(R.id.adjuntar)

        imageView.setOnClickListener {
            imagePicker.launch("image/*")
        }

        btnGuardar.setOnClickListener {
            guardarReceta()
        }

        return view
    }

    private fun guardarReceta() {
        val nombre = editNombre.text.toString().trim()
        val ingredientes = editIngredientes.text.toString().trim()
        val preparacion = editPreparacion.text.toString().trim()
        val imagenUriString = imagenUri?.toString() // Evita crash si es null

        if (nombre.isEmpty() || ingredientes.isEmpty() || preparacion.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
        } else {
            RecetaRepository.listaRecetas.add(
                Receta(
                    nombre = nombre,
                    descripcion = "Agregada por el usuario",
                    ingredientes = ingredientes,
                    pasos = preparacion,
                    imagenUri = imagenUriString
                )
            )

            Toast.makeText(requireContext(), "Receta guardada: $nombre", Toast.LENGTH_SHORT).show()

            editNombre.text.clear()
            editIngredientes.text.clear()
            editPreparacion.text.clear()
            imageView.setImageResource(R.drawable.adjuntar)
            imagenUri = null
        }
    }
}
