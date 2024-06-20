package com.cursos.posgrado.cursoandroidv2

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursos.posgrado.cursoandroidv2.basedatos.Persona
import com.cursos.posgrado.cursoandroidv2.basedatos.PersonaApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private const val PERMISSION_REQUEST_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }

    private lateinit var app: PersonaApp
    private lateinit var personaAdapter: PersonaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = applicationContext as PersonaApp
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.lista)
        val editTextNombre = findViewById<EditText>(R.id.editTextName)
        val editTextEdad = findViewById<EditText>(R.id.edit_text_edad)
        val editTextDireccion = findViewById<EditText>(R.id.edit_text_direccion)
        val btnAdicionar = findViewById<Button>(R.id.boton_agregar)
        personaAdapter = PersonaAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = personaAdapter

        val personaDao = app.room.personaDao()
        val personaViewModel: PersonaViewModel by viewModels { PersonaViewModelFactory(personaDao) }

        personaViewModel.todasPersonas.observe(this, { personas ->
            personaAdapter.setPersonas(personas)
        })
        btnAdicionar.setOnClickListener{
            val nombre = editTextNombre.text.toString()
            val edad = editTextEdad.text.toString().toInt()
            val direccion = editTextDireccion.text.toString()
            lifecycleScope.launch (Dispatchers.IO){
                personaDao.insert(Persona(0,nombre, edad, direccion))
                val actualizacionPersona = personaDao.getAll()
                runOnUiThread {
                    personaAdapter.setPersonas(actualizacionPersona)
                }
            }
            editTextNombre.text.clear()
            editTextEdad.text.clear()
            editTextDireccion.text.clear()

        }


    }

    private fun openCamera() {
        // Aquí puedes implementar la lógica para abrir la cámara
        val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido
                openCamera()
            } else {
                // Permiso denegado
                // Mostrar un mensaje al usuario explicando por qué el permiso es necesario
                Toast.makeText(
                    this,
                    "Permiso de cámara necesario para usar esta función",
                    Toast.LENGTH_SHORT
                ).show()
                // Opcionalmente, puedes llevar al usuario a la configuración de la aplicación
                // para que conceda el permiso manualmente.
                openAppSettings()
            }
        }
    }

    private fun openAppSettings() {
        val settingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        )
        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(settingsIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // La foto fue tomada exitosamente
                Log.d(TAG, "Foto tomada exitosamente.")
            } else if (resultCode == RESULT_CANCELED) {
                // El usuario canceló la captura de la foto
                Log.d(TAG, "Captura de foto cancelada por el usuario.")
            } else {
                // Otra situación inesperada
                Log.e(TAG, "Error al capturar la foto.")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "La funcion onStart se ha iniciado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "La funcion onResume se ha iniciado")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "La funcion onPause se ha iniciado")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "La funcion onStop se ha iniciado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "La funcion onDestroy se ha iniciado")
    }

    private fun animacion(boton: FloatingActionButton) {
        val animacion = ObjectAnimator.ofFloat(boton, "rotationY", 0f, 360f)
        animacion.duration = 600
        animacion.start()
    }
}