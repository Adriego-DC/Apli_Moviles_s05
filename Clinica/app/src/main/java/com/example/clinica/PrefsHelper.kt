package com.example.clinica

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PrefsHelper {
    private const val PREFS_NAME = "MisPrefs"
    private const val KEY_USUARIOS = "usuarios_guardados"
    private const val KEY_USUARIO_ACTUAL = "usuario_actual"

    // Guarda toda la lista de usuarios
    fun guardarListaUsuarios(context: Context, listaUsuarios: List<Usuario>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(listaUsuarios)
        editor.putString(KEY_USUARIOS, json)
        editor.apply()
    }

    // Obtiene la lista completa de usuarios
    fun obtenerUsuariosRegistrados(context: Context): List<Usuario> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_USUARIOS, null)
        return if (json != null) {
            val type = object : TypeToken<List<Usuario>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }

    // Guarda el nombre de usuario actual (para saber quién está logueado)
    fun guardarUsuarioActual(context: Context, nombreUsuario: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_USUARIO_ACTUAL, nombreUsuario).apply()
    }

    // Devuelve el nombre del usuario actual
    fun obtenerUsuarioActual(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_USUARIO_ACTUAL, null)
    }
    fun guardarUsuario(context: Context, nuevoUsuario: Usuario) {
        val usuarios = obtenerUsuariosRegistrados(context).toMutableList()
        usuarios.add(nuevoUsuario)
        guardarListaUsuarios(context, usuarios)
    }
}