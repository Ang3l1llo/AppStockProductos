package org.example.Services

import org.example.Repository.RepoProveedor
import java.util.*

class ServiciosProveedor (val repo : RepoProveedor, val sc : Scanner){

    fun obtenerProveedores() {

        val proveedores = repo.readAll()

        if (proveedores.isEmpty()) {
            println("No hay proveedores")
        } else {
            for (proveedor in proveedores) {
                println("$proveedor")
            }
        }
    }
}