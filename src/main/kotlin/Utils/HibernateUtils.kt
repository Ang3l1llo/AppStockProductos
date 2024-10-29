package org.example.Utils

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

//Se usa object en lugar de class para definir un singleton, así además no necesitas crear una instancia

object HibernateUtils {

    //Mediante el modificador lateinit se declara una variable que no se inicializa en el momento de la declaración
    //pero q se garantiza que sera inicializada antes de ser utilizada
    private lateinit var emf: EntityManagerFactory

    private fun getEntityManagerFactory(namePersistenceUnit : String = ""): EntityManagerFactory {
        return if(this::emf.isInitialized && emf.isOpen) {
            emf;
        } else {
            Persistence.createEntityManagerFactory(namePersistenceUnit)
        }
    }

    fun getEntityManager(namePersistenceUnit: String = ""): EntityManager {
        return getEntityManagerFactory(namePersistenceUnit).createEntityManager()
    }


    // Función para cerrar todos los EntityManagerFactory
    fun shutdown() {
        if (emf.isOpen) {
            emf.close()
        }
    }

    // Función para cerrar un EntityManager específico
    fun closeEntityManager(em: EntityManager?) {
        try {
            if (em != null && em.isOpen) {
                em.close()
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

}