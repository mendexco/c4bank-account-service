package com.mdxco.c4bank.account.infrastructure.jpa.repositories

import com.mdxco.c4bank.account.infrastructure.models.address.AddressModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<AddressModel, String> {
    fun findAllAddressByPostalCode(postalCode: String): List<AddressModel>
}