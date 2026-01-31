package com.m1guelgtz.templatecarsapi.Demo.Core.rutes


sealed class Rutas(val ruta: String) {
    companion object {
        const val RUTA_INICIO = "inicio"
        const val RUTA_PERFIL = "perfil"
        const val RUTA_CONFIGURACION = "configuracion"
        const val RUTA_DETALLES = "detalles/{id}"

    }
}

object RutaInicio : Rutas("inicio")
object RutaPerfil : Rutas("perfil")
object RutaConfiguracion : Rutas("configuracion")
object RutaDetalles : Rutas("detalles/{id}") {
    fun crearRuta(id: Int) = "detalles/$id"
}