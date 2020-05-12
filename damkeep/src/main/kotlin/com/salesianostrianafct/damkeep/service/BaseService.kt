package com.salesianostrianafct.damkeep.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

open class BaseService<T, ID, R : JpaRepository<T, ID>> {

    @Autowired
    lateinit var repository: R

    fun save(t: T): T {
        return repository.save(t)
    }

    fun findById(id: ID): Optional<T> {
        return repository.findById(id)
    }

    fun findAll(): List<T> {
        return repository.findAll()
    }

    fun edit(t: T): T {
        return repository.save(t)
    }

    fun delete(t: T) {
        return repository.delete(t)
    }

    fun deleteById(id: ID) {
        return repository.deleteById(id)
    }
}