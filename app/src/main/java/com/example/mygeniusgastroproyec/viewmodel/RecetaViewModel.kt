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

}
