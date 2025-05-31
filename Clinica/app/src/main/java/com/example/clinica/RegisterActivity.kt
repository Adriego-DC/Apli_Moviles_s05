package com.example.clinica
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class RegisterActivity : AppCompatActivity() {

    private lateinit var etNombres: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNombres = findViewById(R.id.etNombres)
        etApellidos = findViewById(R.id.etApellidos)
        etCorreo = findViewById(R.id.etCorreo)
        etTelefono = findViewById(R.id.etTelefono)
        etContrasena = findViewById(R.id.etContrasena)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            val nombres = etNombres.text.toString()
            val apellidos = etApellidos.text.toString()
            val correo = etCorreo.text.toString()
            val telefono = etTelefono.text.toString()
            val contrasena = etContrasena.text.toString()

            if (nombres.isBlank() || apellidos.isBlank() || correo.isBlank() || telefono.isBlank() || contrasena.isBlank()) {
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!correoValido(correo)) {
                Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoUsuario = Usuario(
                correo = correo,
                apellidos = apellidos,
                nombres = nombres,
                telefono = telefono,
                contrasena = contrasena
            )
            PrefsHelper.guardarUsuario(this, nuevoUsuario)

            startActivity(Intent(this, RegistroExitosoActivity::class.java))
            finish()
        }
    }

    private fun correoValido(correo: String): Boolean {
        val patronCorreo = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|net|org|edu|gov|pe|es|io|info)$")
        return correo.matches(patronCorreo)
    }
}
