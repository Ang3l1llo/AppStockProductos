package org.example.Services

import org.example.Models.Producto
import org.example.Repository.RepoProducto
import org.example.Repository.RepoProveedor
import java.util.*

class ServiciosProducto (val repoProducto : RepoProducto,val repoProovedor : RepoProveedor, val sc : Scanner){

    fun altaProducto() {

        println("Introduce la ID del proovedor: ")
        val idProveedor = sc.nextLong()
        sc.nextLine()

        val proveedor = repoProovedor.read(idProveedor)

        if(proveedor != null){
            println("Nombre del producto: ")
            val nombreProducto = sc.nextLine()

            println("Categoria del producto: ")
            val categoria = sc.nextLine()

            println("Descripción del producto: ")
            val descripcion = sc.nextLine()

            println("Precio sin iva: ")
            val precioSinIva = sc.nextFloat()
            sc.nextLine()

            println("Introduzca stock del producto: ")
            val stock = sc.nextInt()
            sc.nextLine()

            val nuevoProducto = Producto(categoria, nombreProducto, descripcion, precioSinIva, stock, proveedor)

            proveedor.addProducto(nuevoProducto)
            repoProovedor.update(proveedor)

        }else{
            println("No existe ese proveedor, compruebe la ID")
        }
    }

    fun bajaProducto() {

        println("Introduzca la ID del del producto: ")
        val idProducto = sc.nextLine()

        val productoAeliminar = repoProducto.read(idProducto)

        if(productoAeliminar != null){
            val proveedor = repoProducto.readProveedorProducto(idProducto)

            if(proveedor != null){
                proveedor.removeProducto(productoAeliminar)
                repoProovedor.update(proveedor)
                repoProducto.delete(idProducto)
            }

        }else{
            println("No se ha encontrado un producto con esa ID")
        }
    }

    fun modificarNombreProducto() {

        println("Introduzca la ID del producto a modificar: ")
        val idProducto = sc.nextLine()

        println("Introduzca el nuevo nombre del producto: ")
        val nuevoNombre = sc.nextLine()

        repoProducto.updateProductoNombre(idProducto,nuevoNombre)
    }

    fun modificarStockProducto() {

        println("Introduzca la ID del producto a modificar: ")
        val idProducto = sc.nextLine()

        println("Introduzca la nueva cantidad de stock disponible: ")
        val stock = sc.nextInt()
        sc.nextLine()

        repoProducto.updateProductoStock(idProducto, stock)
    }

    fun obtenerProducto() {

        println("Introduzca la ID del producto: ")
        val idProducto = sc.nextLine()

        val producto = repoProducto.read(idProducto)

        if(producto != null){
            println(producto)

        }else{
            println("No hay ningún producto con dicha ID")
        }
    }

    fun obtenerProductosConStock() {

        val productos = repoProducto.readProductoConStock()
        println("Cantidad de productos: ${productos.size}")

        if(productos.isNotEmpty()){

            for(producto in productos)
                println(producto)

        }else{
            println("No hay stock")
        }
    }

    fun obtenerProductosSinStock() {

        val productos = repoProducto.readProductoSinStock()

        for(producto in productos){
            println(producto)
        }
    }

    fun obtenerProveedorProducto() {

        println("Introduzca ID del producto: ")
        val idProducto = sc.nextLine()

        val proveedor = repoProducto.readProveedorProducto(idProducto)

        if(proveedor != null){
            println(proveedor)

        }else{
            println("Esa ID no corresponde con ningún proveedor")
        }
    }
}
