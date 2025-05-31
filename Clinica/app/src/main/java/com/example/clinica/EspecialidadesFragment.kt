package com.example.clinica
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EspecialidadesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EspecialidadAdapter
    private lateinit var listaEspecialidades: List<Especialidad>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_especialidades, container, false)
        recyclerView = view.findViewById(R.id.recyclerEspecialidades)

        listaEspecialidades = listOf(
            Especialidad("Medicina General", "Atención primaria para toda la familia. Aquí realizamos chequeos generales, diagnóstico de enfermedades comunes, manejo de dolencias y prevención para mantener tu salud en óptimas condiciones."),
            Especialidad("Pediatría", "Cuidado integral para bebés, niños y adolescentes. Nos encargamos del seguimiento de su crecimiento, vacunas, tratamiento de enfermedades infantiles y orientación para el desarrollo saludable."),
            Especialidad("Ginecología y Obstetricia", "Especialistas en salud femenina, desde chequeos ginecológicos rutinarios hasta atención prenatal, control de embarazo, parto y seguimiento postnatal, asegurando el bienestar de la madre y el bebé."),
            Especialidad("Cardiología", "Diagnóstico y tratamiento de enfermedades del corazón y del sistema circulatorio. Controlamos factores de riesgo, realizamos estudios especializados y diseñamos planes para cuidar tu salud cardiovascular."),
            Especialidad("Dermatología", "Atención especializada en piel, cabello y uñas. Tratamos afecciones comunes como acné, dermatitis, infecciones y realizamos evaluaciones para detectar signos tempranos de enfermedades cutáneas."),
            Especialidad("Traumatología y Ortopedia", "Cuidado de huesos, músculos y articulaciones. Atendemos lesiones, fracturas, problemas musculares y enfermedades que afectan el sistema músculo-esquelético, para ayudarte a recuperar tu movilidad."),
            Especialidad("Neurología", "Diagnóstico y tratamiento de enfermedades que afectan el sistema nervioso central y periférico, como migrañas, epilepsia, trastornos del movimiento y otras condiciones neurológicas."),
            Especialidad("Psiquiatría / Psicología", "Apoyo en salud mental para mejorar tu bienestar emocional. Ofrecemos evaluación, diagnóstico y tratamiento de trastornos mentales, además de terapias para manejar estrés, ansiedad y otras dificultades."),
            Especialidad("Oftalmología", "Cuidado de la visión y salud ocular. Realizamos exámenes visuales, tratamiento de enfermedades oculares y cirugías para preservar o mejorar tu salud visual."),
            Especialidad("Otorrinolaringología", "Atención para enfermedades y problemas relacionados con oídos, nariz y garganta. Tratamos infecciones, alergias, problemas respiratorios y dificultades auditivas."),
            Especialidad("Gastroenterología", "Diagnóstico y tratamiento de trastornos del sistema digestivo, incluyendo estómago, intestinos, hígado y páncreas, para mantener una buena salud digestiva."),
            Especialidad("Urología", "Cuidado del sistema urinario y reproductor masculino. Tratamos infecciones, cálculos renales, problemas prostáticos y otras condiciones urológicas."),
            Especialidad("Endocrinología", "Especialistas en el manejo de trastornos hormonales, como diabetes, problemas tiroideos y otras enfermedades metabólicas, ayudándote a mantener el equilibrio hormonal."),
            Especialidad("Reumatología", "Diagnóstico y tratamiento de enfermedades autoinmunes y trastornos articulares, como artritis y lupus, que afectan tus huesos y tejidos conectivos."),
            Especialidad("Neumología", "Atención especializada en enfermedades del aparato respiratorio, como asma, bronquitis y otras patologías pulmonares, para asegurar una buena función respiratoria.")
        )

        adapter = EspecialidadAdapter(listaEspecialidades) { especialidad ->
            mostrarDialogo(especialidad)
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        return view
    }

    private fun mostrarDialogo(especialidad: Especialidad) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_especialidad, null)
        val tvTitulo = dialogView.findViewById<TextView>(R.id.tvDialogTitulo)
        val tvDescripcion = dialogView.findViewById<TextView>(R.id.tvDialogDescripcion)
        val ivImagen = dialogView.findViewById<ImageView>(R.id.ivDialogImagen)

        tvTitulo.text = especialidad.titulo
        tvDescripcion.text = especialidad.descripcion

        // Convertir título a nombre de recurso (minúsculas, sin espacios, sin acentos)
        val nombreImagen = especialidad.titulo
            .lowercase()
            .replace(" ", "")
            .replace("á", "a")
            .replace("é", "e")
            .replace("í", "i")
            .replace("ó", "o")
            .replace("ú", "u")
            .replace("/", "") // quitar barras si hay
            .replace(",", "")
            .replace("-", "")

        val context = requireContext()
        val resId = context.resources.getIdentifier(nombreImagen, "drawable", context.packageName)

        if (resId != 0) {
            ivImagen.setImageResource(resId)
        } else {
            ivImagen.setImageResource(R.drawable.img_default) // pon una imagen por defecto si no encuentra
        }

        val dialog = android.app.AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }


}
