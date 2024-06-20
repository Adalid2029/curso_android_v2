package com.cursos.posgrado.cursoandroidv2.basedatos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Persona(
    @PrimaryKey(autoGenerate = true)
    val id_persona: Int,
    val nombre: String,
    val edad: Int,
    val direccion: String
)