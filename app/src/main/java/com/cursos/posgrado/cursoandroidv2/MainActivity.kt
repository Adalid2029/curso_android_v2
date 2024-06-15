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
import android.widget.Toast

class MainActivity : AppCompatActivity(){
    companion object {
        private const val TAG = "MainActivity"
        private const val PERMISSION_REQUEST_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val boton:FloatingActionButton = findViewById(R.id.boton)
        boton.setOnClickListener(){
            animacion(boton)
        }
        Log.d("MainActivity", "la funcion onCreate se ha iniciado ")

        // Verificar si el permiso de la camara ha sido concedido
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            // Si el permiso no ha sido concedido
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CODE)
        }else{
            openCamera()
        }
    }
    private fun openCamera() {
        // Aquí puedes implementar la lógica para abrir la cámara
        val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido
                openCamera()
            } else {
                // Permiso denegado
                // Mostrar un mensaje al usuario explicando por qué el permiso es necesario
                Toast.makeText(this, "Permiso de cámara necesario para usar esta función", Toast.LENGTH_SHORT).show()
                // Opcionalmente, puedes llevar al usuario a la configuración de la aplicación
                // para que conceda el permiso manualmente.
                openAppSettings()
            }
        }
    }
    private fun openAppSettings() {
        val settingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null))
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
        Log.d("MainActivity","La funcion onStart se ha iniciado")
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
    private fun animacion(boton: FloatingActionButton){
        val animacion = ObjectAnimator.ofFloat(boton, "rotationY", 0f, 360f)
        animacion.duration = 600
        animacion.start()
    }
}