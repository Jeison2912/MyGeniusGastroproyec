package com.example.mygeniusgastroproyec.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recetas")
data class RecetaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val ingredientes: String,
    val preparacion: String,
    val imagenUri: String,
    val autor: String // 👈 Este campo es obligatorio si lo tienes en el constructor
)

