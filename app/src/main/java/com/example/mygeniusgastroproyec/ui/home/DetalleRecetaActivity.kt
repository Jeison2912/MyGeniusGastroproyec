package com.example.mygeniusgastroproyec.ui.home

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mygeniusgastroproyec.R
import com.example.mygeniusgastroproyec.utils.SessionManager
import com.example.mygeniusgastroproyec.viewmodel.RecetaViewModel
import java.io.FileNotFoundException

class DetalleRecetaActivity : AppCompatActivity() {

    private val recetaViewModel: RecetaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_receta)


        val nombre = intent.getStringExtra("nombre") ?: "Sin nombre"
        val descripcion = intent.getStringExtra("descripcion") ?: "Sin descripción"
        val ingredientes = intent.getStringExtra("ingredientes") ?: "Sin ingredientes"
        val pasos = intent.getStringExtra("pasos") ?: "Sin pasos"
        val imagenResId = intent.getIntExtra("imagenResId", 0)
        val imagenUri = intent.getStringExtra("imagenUri")
        val autor = intent.getStringExtra("autor") ?: ""
        val recetaId = intent.getIntExtra("id", -1)

        val usuarioActual = SessionManager.getUsuario(this)

        val imagenReceta: ImageView = findViewById(R.id.imageDetalle)
        val tituloReceta: TextView = findViewById(R.id.textTitulo)
        val descripcionReceta: TextView = findViewById(R.id.textDescripcion)
        val ingredientesReceta: TextView = findViewById(R.id.textIngredientes)
        val pasosReceta: TextView = findViewById(R.id.textPasos)
        val btnEliminar: Button = findViewById(R.id.btnEliminar)
        val btnEditar: Button = findViewById(R.id.btnEditar)

        tituloReceta.text = nombre
        descripcionReceta.text = descripcion
        ingredientesReceta.text = ingredientes
        pasosReceta.text = pasos

        if (!imagenUri.isNullOrEmpty()) {
            try {
                val uri = Uri.parse(imagenUri)
                val inputStream = contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imagenReceta.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                imagenReceta.setImageResource(R.drawable.fav1)
            }
        } else if (imagenResId != 0) {
            imagenReceta.setImageResource(imagenResId)
        } else {
            imagenReceta.setImageResource(R.drawable.fav1)
        }

        if (autor == usuarioActual && recetaId != -1) {
            btnEditar.visibility = View.VISIBLE
            btnEliminar.visibility = View.VISIBLE
        } else {
            btnEditar.visibility = View.GONE
            btnEliminar.visibility = View.GONE
        }

        btnEliminar.setOnClickListener {
            recetaViewModel.eliminarPorId(recetaId)
            Toast.makeText(this, "Receta eliminada", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnEditar.setOnClickListener {
            val intent = Intent(this, EditarRecetaActivity::class.java).apply {
                putExtra("id", recetaId)
                putExtra("nombre", nombre)
                putExtra("ingredientes", ingredientes)
                putExtra("preparacion", pasos)
                putExtra("imagenUri", imagenUri)
                putExtra("modoEdicion", true)
            }
            startActivity(intent)
            finish()
        }
    }
}
