package com.mdxco.c4bank.account.infrastructure.jpa.repositories

import com.mdxco.c4bank.account.infrastructure.models.account.AccountModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountModel, String> {
    fun existsByAddressId(addressId: String): Boolean
    fun existsByTaxIdentifier(taxIdentifier: String): Boolean
    fun findTopByOrderByIdDesc(): AccountModel?
}