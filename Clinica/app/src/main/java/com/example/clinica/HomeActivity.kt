package com.example.clinica
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var tvTitulo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val usuarioActualEmail = PrefsHelper.obtenerUsuarioActual(this)
        val usuario = UserData.usuarioActual ?: PrefsHelper.obtenerUsuariosRegistrados(this)
            .find { it.correo == usuarioActualEmail }

        if (usuario != null) {
            UserData.usuarioActual = usuario
        }

        tvTitulo = findViewById(R.id.tvTitulo)
        cambiarFragment(MenuFragment(), "Menú")

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_nav -> {
                    cambiarFragment(MenuFragment(), "Menú")
                    true
                }
                R.id.especialidades_nav -> {
                    cambiarFragment(EspecialidadesFragment(), "Especialidades")
                    true
                }
                R.id.perfil_nav -> {
                    cambiarFragment(PerfilFragment(), "Perfil")
                    true
                }
                else -> false
            }
        }
    }

    private fun cambiarFragment(fragment: Fragment, titulo: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .commit()
        tvTitulo.text = titulo
    }
}
