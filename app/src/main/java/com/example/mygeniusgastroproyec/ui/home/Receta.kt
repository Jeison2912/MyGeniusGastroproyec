package com.example.mygeniusgastroproyec.ui.home

data class Receta(
    val nombre: String,
    val descripcion: String = "",  // ahora tiene un valor por defecto
    val imagenResId: Int = 0,
    val ingredientes: String,
    val pasos: String
)
