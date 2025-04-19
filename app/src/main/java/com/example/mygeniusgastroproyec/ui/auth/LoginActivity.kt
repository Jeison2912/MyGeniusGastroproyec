package com.example.mygeniusgastroproyec.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mygeniusgastroproyec.MainActivity
import com.example.mygeniusgastroproyec.R
import com.example.mygeniusgastroproyec.utils.SessionManager

class LoginActivity : AppCompatActivity() {

    private lateinit var editUsuario: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnIrARegistro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (SessionManager.isLoggedIn(this)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setContentView(R.layout.activity_login)

        editUsuario = findViewById(R.id.editUsuario)
        editPassword = findViewById(R.id.editPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnIrARegistro = findViewById(R.id.btnIrARegistro)

        btnLogin.setOnClickListener {
            val usuario = editUsuario.text.toString()
            val password = editPassword.text.toString()

            if (usuario == SessionManager.getUsuario(this) && password == SessionManager.getPassword(this)) {
                SessionManager.setLoggedIn(this, true)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        btnIrARegistro.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }
}
