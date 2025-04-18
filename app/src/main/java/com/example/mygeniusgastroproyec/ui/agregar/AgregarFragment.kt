package com.example.mygeniusgastroproyec.ui.receta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mygeniusgastroproyec.R

class AgregarFragment : Fragment() {

    private lateinit var editNombre: EditText
    private lateinit var editIngredientes: EditText
    private lateinit var editPreparacion: EditText
    private lateinit var btnGuardar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_agregar, container, false)

        editNombre = view.findViewById(R.id.editNombreReceta)
        editIngredientes = view.findViewById(R.id.editIngredientes)
        editPreparacion = view.findViewById(R.id.editPreparacion)
        btnGuardar = view.findViewById(R.id.btnGuardarReceta)

        btnGuardar.setOnClickListener {
            guardarReceta()
        }

        return view
    }

    private fun guardarReceta() {
        val nombre = editNombre.text.toString().trim()
        val ingredientes = editIngredientes.text.toString().trim()
        val preparacion = editPreparacion.text.toString().trim()

        if (nombre.isEmpty() || ingredientes.isEmpty() || preparacion.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
        } else {
            // Aquí luego puedes guardar la receta en una BD o en otro lado
            Toast.makeText(requireContext(), "Receta guardada: $nombre", Toast.LENGTH_SHORT).show()

            // Opcional: limpiar los campos
            editNombre.text.clear()
            editIngredientes.text.clear()
            editPreparacion.text.clear()
        }
    }
}
