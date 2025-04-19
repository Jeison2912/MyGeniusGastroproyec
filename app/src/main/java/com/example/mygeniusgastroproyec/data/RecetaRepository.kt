package com.example.mygeniusgastroproyec.data

import androidx.lifecycle.LiveData

class RecetaRepository(private val recetaDao: RecetaDao) {

    val todasLasRecetas: LiveData<List<RecetaEntity>> = recetaDao.obtenerTodas()

    suspend fun insertar(receta: RecetaEntity) {
        recetaDao.insertar(receta)
    }

    suspend fun actualizar(receta: RecetaEntity) {
        recetaDao.actualizar(receta)
    }

    suspend fun eliminarPorId(id: Int) {
        recetaDao.eliminarPorId(id)
    }
}


