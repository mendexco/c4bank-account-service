package com.mdxco.c4bank.infrastructure.h2.repositories

import com.mdxco.c4bank.infrastructure.models.account.AccountModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountModel, String> {
    fun existsByTaxIdentifier(taxIdentifier: String): Boolean
}