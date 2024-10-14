package com.mdxco.c4bank.infrastructure.gateways.account

import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.gateways.AccountGateway
import com.mdxco.c4bank.infrastructure.h2.repositories.AccountRepository
import com.mdxco.c4bank.infrastructure.models.account.toModel
import org.springframework.stereotype.Component

@Component
class AccountGatewayImpl(
    private val accountRepository: AccountRepository
) : AccountGateway {
    override fun checkIfAccountIsCreated(taxIdentifier: String): Boolean {
        return accountRepository.existsByTaxIdentifier(taxIdentifier)
    }

    override fun getLatestAccountNumber(): String? {
        return accountRepository.findTopByOrderByIdDesc()?.accountNumber
    }

    override fun getById(id: String): Account? {
        return accountRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun saveAccount(account: Account): Account {
        return accountRepository.save(account.toModel()).toDomain()
    }
}