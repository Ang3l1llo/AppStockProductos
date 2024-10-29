package org.example
import org.example.Models.Proveedor
import org.example.Repository.RepoProducto
import org.example.Repository.RepoProveedor
import org.example.Repository.RepoUsuario
import org.example.Services.ServiciosProducto
import org.example.Services.ServiciosProveedor
import org.example.Services.ServiciosUsuario
import org.example.Utils.HibernateUtils
import java.util.Scanner

fun main(){

    val sc = Scanner(System.`in`)
    val serviciosUsuario = ServiciosUsuario(RepoUsuario(), sc)
    val serviciosProducto = ServiciosProducto(RepoProducto(), RepoProveedor(), sc)
    val serviciosProveedor = ServiciosProveedor(RepoProveedor(), sc)
    val repoProveedor = RepoProveedor()
    var opcion : Int

    val proveedorDePrueba = Proveedor("Luis","c/vlad tepes")
    repoProveedor.create(proveedorDePrueba)

    var salir = false //para controlar el login

    salir = login(sc,serviciosUsuario)

    if(!salir){

        do {
            println("Seleccione una opción: ")
            println("1. Dar de alta un producto")
            println("2. Dar de baja un producto")
            println("3. Modificar nombre de un producto")
            println("4. Modificar stock de un producto")
            println("5. Obtener un producto")
            println("6. Obtener productos con stock")
            println("7. Obtener productos sin stock")
            println("8. Obtener proveedor de un producto")
            println("9. Obtener todos los proveedores")
            println("10. Salir")

            opcion = sc.nextInt()
            sc.nextLine()

            when(opcion) {

                1 -> serviciosProducto.altaProducto()
                2 -> serviciosProducto.bajaProducto()
                3 -> serviciosProducto.modificarNombreProducto()
                4 -> serviciosProducto.modificarStockProducto()
                5 -> serviciosProducto.obtenerProducto()
                6 -> serviciosProducto.obtenerProductosConStock()
                7 -> serviciosProducto.obtenerProductosSinStock()
                8 -> serviciosProducto.obtenerProveedorProducto()
                9 -> serviciosProveedor.obtenerProveedores()
                10 -> println("Finalizando programa..")
                else -> println("Opción no válida. Por favor, intente nuevamente.")
            }

        }while(opcion != 10)
    }


}

fun login(sc: Scanner, serviciosUsuario: ServiciosUsuario): Boolean {
    var opcion: Int

    do {
        println("Seleccione una opción: ")
        println("1. Crear usuario")
        println("2. Acceder con mi usuario")
        println("3. Salir")

        opcion = sc.nextInt()
        sc.nextLine()

        when (opcion) {
            1 -> serviciosUsuario.crearUsuario()
            2 -> {
                if (serviciosUsuario.comprobarUsuario()) {
                    println("Acceso correcto")
                    return false // Rompe el bucle y devuelve false para continuar
                } else {
                    println("Usuario o contraseña incorrectos.")
                }
            }
            3 -> {
                println("Saliendo, espere un momento..")
                return true
            }
            else -> println("Opción no válida. Por favor, intente nuevamente.")
        }
    } while (true)
}
