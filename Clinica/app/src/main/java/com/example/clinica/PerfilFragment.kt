package com.example.clinica
import com.example.clinica.UserData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PerfilFragment : Fragment() {

    private lateinit var tvNombre: TextView
    private lateinit var tvApellidos: TextView
    private lateinit var tvCorreo: TextView
    private lateinit var tvTelefono: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        tvNombre = view.findViewById(R.id.tvNombre)
        tvApellidos = view.findViewById(R.id.tvApellidos)
        tvCorreo = view.findViewById(R.id.tvCorreo)
        tvTelefono = view.findViewById(R.id.tvTelefono)

        mostrarDatosUsuario()

        return view
    }

    private fun mostrarDatosUsuario() {
        val usuario = UserData.usuarioActual

        if (usuario != null) {
            tvNombre.text = "Nombres: ${usuario.nombres}"
            tvApellidos.text = "Apellidos: ${usuario.apellidos}"
            tvCorreo.text = "Correo: ${usuario.correo}"
            tvTelefono.text = "Teléfono: ${usuario.telefono}"
        } else {
            tvNombre.text = "Nombres: -"
            tvApellidos.text = "Apellidos: -"
            tvCorreo.text = "Correo: -"
            tvTelefono.text = "Teléfono: -"
        }
    }
}
