package com.cursos.posgrado.cursoandroidv2.basedatos

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Persona::class],
    version = 1
)
abstract class PersonaDb:RoomDatabase(){
    abstract fun personaDao():PersonaDao
}