package com.cursos.posgrado.cursoandroidv2.basedatos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonaDao {
    @Query("SELECT * FROM Persona ORDER BY id_persona DESC")
    suspend fun getAll(): List<Persona>

    @Query("SELECT * FROM Persona WHERE id_persona = :id")
    suspend fun getById(id: Int):Persona

    @Update
    suspend fun update(persona: Persona)

    @Insert
    suspend fun insert(persona: Persona)

    @Delete
    suspend fun delete(persona: Persona)

}