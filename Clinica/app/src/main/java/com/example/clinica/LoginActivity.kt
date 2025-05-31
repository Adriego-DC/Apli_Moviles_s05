package com.example.clinica
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
class LoginActivity : AppCompatActivity() {
    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegistrar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContrasena)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegistrar = findViewById(R.id.tvRegistrar)

        btnLogin.setOnClickListener {
            val usuarioInput = etUsuario.text.toString().trim()
            val contrasenaInput = etContrasena.text.toString().trim()

            if (usuarioInput.isNotBlank() && contrasenaInput.isNotBlank()) {
                // Obtener la lista de usuarios guardados
                val usuariosRegistrados = PrefsHelper.obtenerUsuariosRegistrados(this)

                // Buscar usuario por nombre de usuario
                val usuarioEncontrado = usuariosRegistrados.find { it.correo == usuarioInput }

                if (usuarioEncontrado != null && usuarioEncontrado.contrasena == contrasenaInput) {
                    // Login exitoso
                    UserData.usuarioActual = usuarioEncontrado
                    PrefsHelper.guardarUsuarioActual(this, usuarioEncontrado.correo)
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegistrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
