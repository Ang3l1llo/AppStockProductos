package org.example.Services

import org.example.Models.Usuario
import org.example.Repository.RepoUsuario
import java.util.*

class ServiciosUsuario(val repo : RepoUsuario, val sc : Scanner) {

    fun crearUsuario() {

        println("Introduzca nuevo nombre de usuario:")
        val userName : String = sc.nextLine()

        println("Introduzca contraseña:")
        val password : String = sc.nextLine()

        val usuario = repo.read(userName)

        if(usuario == null){
            val nuevoUsuario = Usuario(password, userName)
            repo.create(nuevoUsuario)
        }else{
            println("El usuario ya existe")
        }
    }

    fun comprobarUsuario() : Boolean {

        println("Introduzca su nombre de usuario: ")
        val userName : String = sc.nextLine()

        println("Introduzca su contraseña: ")
        val password : String = sc.nextLine()

        val posibleUsuario = repo.read(userName)

        return if(posibleUsuario != null){
            posibleUsuario.password == password

        }else{
            println("Usuario o contraseña incorrectos")
            false
        }
    }
}