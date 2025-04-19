package com.example.mygeniusgastroproyec.ui.home

data class Receta(
    val nombre: String,
    val descripcion: String,
    val ingredientes: String,
    val pasos: String,
    val imagenResId: Int = 0,
    val imagenUri: String? = null
)
