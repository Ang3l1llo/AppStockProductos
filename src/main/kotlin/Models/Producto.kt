package org.example.Models

import jakarta.persistence.*
import java.time.Instant
import java.util.Date

@Entity
@Table(name = "productos")
class Producto (

    @Column(name = "categoria")
    val categoria : String?,

    @Column(name = "nombre", nullable = false)
    var nombre : String?,

    @Column(name = "descripcion")
    val descripcion : String?,

    @Column(name = "precio_sin_iva")
    var precioSinIva : Float?,

    @Column(name = "precio_con_iva")
    var precioConIva : Float? = precioSinIva?.times(1.21f),

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    val fechaAlta: Date = Date.from(Instant.now()),

    @Column(name = "stock")
    var stock : Int?,

    //Merge para que se actualice en ambas tablas
    @ManyToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @JoinColumn(name = "id_proveedor")
    var proveedor : Proveedor?,

    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id : String,

){
    //Como no quiero a la hora de crear un producto todos los parámetros
    //Puesto que no tiene sentido establecer precio con iva o la id(autogenerada) lo hago así
    constructor(categoria: String, nombre: String, descripcion: String, precioSinIva: Float, stock: Int, proveedor: Proveedor)
    : this(categoria, nombre, descripcion, precioSinIva,precioSinIva*1.21f, Date.from(Instant.now()), stock,
        proveedor,"${categoria.take(3)}${nombre.take(3)}${proveedor.nombre?.take(3)}")

    //Para los métodos de obtener productos
    override fun toString(): String {
        return "Producto(id='$id', categoria='$categoria', nombre='$nombre', descripcion='$descripcion', " +
                "precioSinIva=$precioSinIva, precioConIva=$precioConIva, fechaAlta=$fechaAlta, stock=$stock, " +
                "proveedor=${proveedor?.nombre})"
    }
}
