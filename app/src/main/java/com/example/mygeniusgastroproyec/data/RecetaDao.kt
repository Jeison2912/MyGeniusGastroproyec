package com.example.mygeniusgastroproyec.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecetaDao {

    @Insert
    suspend fun insertar(receta: RecetaEntity)

    @Update
    suspend fun actualizar(receta: RecetaEntity)

    @Query("DELETE FROM recetas WHERE id = :id")
    suspend fun eliminarPorId(id: Int)

    @Query("SELECT * FROM recetas ORDER BY id DESC")
    fun obtenerTodas(): LiveData<List<RecetaEntity>>
}
