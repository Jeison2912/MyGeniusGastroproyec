package com.example.mygeniusgastroproyec.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mygeniusgastroproyec.R
import com.example.mygeniusgastroproyec.utils.SessionManager

class RegistroActivity : AppCompatActivity() {

    private lateinit var editUsuario: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        editUsuario = findViewById(R.id.editUsuario)
        editPassword = findViewById(R.id.editPassword)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            val usuario = editUsuario.text.toString()
            val password = editPassword.text.toString()

            if (usuario.isNotEmpty() && password.isNotEmpty()) {
                SessionManager.saveUser(this, usuario, password)
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Por favor completa los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
