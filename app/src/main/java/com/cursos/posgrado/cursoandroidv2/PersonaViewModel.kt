package com.cursos.posgrado.cursoandroidv2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cursos.posgrado.cursoandroidv2.basedatos.PersonaDao
import kotlinx.coroutines.Dispatchers

class PersonaViewModel(private val personaDao: PersonaDao): ViewModel() {
    val todasPersonas = liveData(Dispatchers.IO) {
        val data = personaDao.getAll()
        emit(data)
    }
}
class PersonaViewModelFactory(private val personaDao: PersonaDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PersonaViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PersonaViewModel(personaDao) as T
        }
        throw IllegalArgumentException("Error no se puede importar")
    }
}