package com.example.mygeniusgastroproyec.ui.home

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mygeniusgastroproyec.R

class DetalleRecetaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_receta)

        // Recuperar los datos enviados desde el adapter
        val nombre = intent.getStringExtra("nombre") ?: "Nombre no disponible"
        val descripcion = intent.getStringExtra("descripcion") ?: "Descripción no disponible"
        val ingredientes = intent.getStringExtra("ingredientes") ?: "Ingredientes no disponibles"
        val pasos = intent.getStringExtra("pasos") ?: "Pasos no disponibles"
        val imagenResId = intent.getIntExtra("imagenResId", R.drawable.fav1)

        // Enlazar con los elementos del layout
        val imagenReceta: ImageView = findViewById(R.id.imageDetalle)
        val tituloReceta: TextView = findViewById(R.id.textTitulo)
        val descripcionReceta: TextView = findViewById(R.id.textDescripcion)
        val ingredientesReceta: TextView = findViewById(R.id.textIngredientes)
        val pasosReceta: TextView = findViewById(R.id.textPasos)

        // Asignar los valores a cada vista
        tituloReceta.text = nombre
        descripcionReceta.text = descripcion
        ingredientesReceta.text = ingredientes
        pasosReceta.text = pasos

        // Verificar si el recurso de imagen es válido y mostrarlo
        if (imagenResId != 0 && imagenResId != R.drawable.fav1) {
            imagenReceta.setImageResource(imagenResId)
        } else {
            imagenReceta.setImageResource(R.drawable.fav1) // Imagen por defecto
        }
    }
}
