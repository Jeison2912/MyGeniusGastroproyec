package com.example.mygeniusgastroproyec.ui.home

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mygeniusgastroproyec.R
import com.example.mygeniusgastroproyec.data.RecetaEntity
import com.example.mygeniusgastroproyec.viewmodel.RecetaViewModel
import java.io.FileNotFoundException

class EditarRecetaActivity : AppCompatActivity() {

    private lateinit var viewModel: RecetaViewModel
    private var imagenUri: String? = null
    private var recetaId: Int = -1

    private lateinit var imageEditar: ImageView
    private lateinit var editNombre: EditText
    private lateinit var editIngredientes: EditText
    private lateinit var editPreparacion: EditText
    private lateinit var btnActualizar: Button

    private val imagePicker = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            imagenUri = uri.toString()
            try {
                val inputStream = contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageEditar.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                Toast.makeText(this, "Error al cargar imagen", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_receta)

        viewModel = ViewModelProvider(this)[RecetaViewModel::class.java]

        imageEditar = findViewById(R.id.imageEditar)
        editNombre = findViewById(R.id.editNombre)
        editIngredientes = findViewById(R.id.editIngredientes)
        editPreparacion = findViewById(R.id.editPreparacion)
        btnActualizar = findViewById(R.id.btnActualizar)

        recetaId = intent.getIntExtra("id", -1)
        val nombre = intent.getStringExtra("nombre") ?: ""
        val ingredientes = intent.getStringExtra("ingredientes") ?: ""
        val preparacion = intent.getStringExtra("pasos") ?: ""
        imagenUri = intent.getStringExtra("imagenUri")

        editNombre.setText(nombre)
        editIngredientes.setText(ingredientes)
        editPreparacion.setText(preparacion)

        if (!imagenUri.isNullOrEmpty()) {
            try {
                val inputStream = contentResolver.openInputStream(Uri.parse(imagenUri))
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageEditar.setImageBitmap(bitmap)
            } catch (e: Exception) {
                imageEditar.setImageResource(R.drawable.adjuntar)
            }
        }

        imageEditar.setOnClickListener {
            imagePicker.launch("image/*")
        }

        btnActualizar.setOnClickListener {
            val nuevoNombre = editNombre.text.toString().trim()
            val nuevosIngredientes = editIngredientes.text.toString().trim()
            val nuevaPreparacion = editPreparacion.text.toString().trim()

            if (recetaId != -1 && nuevoNombre.isNotEmpty() && nuevosIngredientes.isNotEmpty() && nuevaPreparacion.isNotEmpty()) {
                val recetaActualizada = RecetaEntity(
                    id = recetaId,
                    nombre = nuevoNombre,
                    ingredientes = nuevosIngredientes,
                    preparacion = nuevaPreparacion,
                    imagenUri = imagenUri ?: "",
                    autor = com.example.mygeniusgastroproyec.utils.SessionManager.getUsuario(this)
                )
                viewModel.actualizar(recetaActualizada)
                Toast.makeText(this, "Receta actualizada", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
