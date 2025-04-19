package com.example.mygeniusgastroproyec.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mygeniusgastroproyec.data.AppDatabase
import com.example.mygeniusgastroproyec.data.RecetaEntity
import com.example.mygeniusgastroproyec.data.RecetaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecetaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecetaRepository
    val todasLasRecetas: LiveData<List<RecetaEntity>>

    init {
        val recetaDao = AppDatabase.getDatabase(application).recetaDao()
        repository = RecetaRepository(recetaDao)
        todasLasRecetas = repository.todasLasRecetas

        // Insertar recetas predefinidas al iniciar si es necesario
        insertarRecetasPredefinidas()
    }

    fun insertar(receta: RecetaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertar(receta)
        }
    }

    fun eliminarPorId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.eliminarPorId(id)
        }
    }

    fun actualizar(receta: RecetaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.actualizar(receta)
        }
    }

    private fun insertarRecetasPredefinidas() {
        viewModelScope.launch(Dispatchers.IO) {
            val recetasActuales = todasLasRecetas.value
            if (recetasActuales.isNullOrEmpty()) {
                val recetas = listOf(
                    RecetaEntity(0, "Pizza Margarita", "Harina, Tomate, Queso Mozzarella, Albahaca", "1. Prepara la masa. 2. Agrega salsa de tomate. 3. Hornea.", "", "Admin"),
                    RecetaEntity(0, "Tortilla de Patatas", "Huevos, Patatas, Cebolla, Aceite de oliva", "1. Fríe patatas y cebolla. 2. Añade huevo. 3. Cocina.", "", "Admin"),
                    RecetaEntity(0, "Arepas Rellenas", "Harina de maíz, Agua, Queso, Jamón", "1. Forma las arepas. 2. Cocina. 3. Rellena.", "", "Admin"),
                    RecetaEntity(0, "Spaghetti Boloñesa", "Spaghetti, Carne, Tomate, Ajo", "1. Cocina la pasta. 2. Prepara la salsa. 3. Mezcla.", "", "Admin"),
                    RecetaEntity(0, "Panqueques", "Harina, Huevo, Leche, Azúcar", "1. Mezcla. 2. Cocina en sartén. 3. Sirve.", "", "Admin"),
                    RecetaEntity(0, "Guacamole", "Aguacate, Cebolla, Tomate, Limón", "1. Tritura aguacate. 2. Mezcla todo.", "", "Admin"),
                    RecetaEntity(0, "Ceviche de Camarón", "Camarón, Limón, Cebolla, Tomate", "1. Marina los camarones. 2. Mezcla ingredientes.", "", "Admin"),
                    RecetaEntity(0, "Pollo al Curry", "Pollo, Curry, Leche de coco", "1. Sofríe. 2. Agrega leche de coco. 3. Cocina.", "", "Admin"),
                    RecetaEntity(0, "Ensalada César", "Lechuga, Pollo, Queso, Pan", "1. Mezcla todo. 2. Agrega aderezo.", "", "Admin"),
                    RecetaEntity(0, "Brownies", "Chocolate, Mantequilla, Azúcar, Huevo", "1. Mezcla ingredientes. 2. Hornea 30 min.", "", "Admin")
                )

                recetas.forEach {
                    repository.insertar(it)
                }
            }
        }
    }
}
