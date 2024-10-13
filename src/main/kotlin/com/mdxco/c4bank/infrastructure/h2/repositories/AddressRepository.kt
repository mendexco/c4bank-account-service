package com.mdxco.c4bank.infrastructure.h2.repositories

import com.mdxco.c4bank.infrastructure.models.address.AddressModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<AddressModel, Long> {
    fun findAddressByPostalCode(postalCode: String): AddressModel?
}