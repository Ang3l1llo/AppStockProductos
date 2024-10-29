package org.example.Models

import jakarta.persistence.*

@Entity
@Table(name = "proveedores")
class Proveedor(

    @Column(name = "nombre", nullable = false)
    var nombre: String?,

    @Column(name = "direccion")
    val direccion: String?,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "proveedor", orphanRemoval = true, fetch = FetchType.EAGER)
    var productos: MutableList<Producto> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

)
    {

    fun addProducto(producto: Producto) {
        productos.add(producto)
        producto.proveedor = this //referencia producto al proveedor
    }

    fun removeProducto(producto: Producto) {
        productos.remove(producto)
    }

    //Para obtener un proveedor
    override fun toString(): String {
        return "[$nombre] Dirección: $direccion, Id: $id, Productos: $productos"
    }
}