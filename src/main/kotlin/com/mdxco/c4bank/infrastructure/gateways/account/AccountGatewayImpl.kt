package com.mdxco.c4bank.infrastructure.gateways.account

import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.gateways.AccountGateway
import com.mdxco.c4bank.infrastructure.h2.repositories.account.AccountRepository
import com.mdxco.c4bank.infrastructure.h2.repositories.account.models.toModel
import org.springframework.stereotype.Component

@Component
class AccountGatewayImpl(
    private val accountRepository: AccountRepository
) : AccountGateway {
    override fun checkIfAccountIsCreated(taxIdentifier: String): Boolean {
        return accountRepository.existsByTaxIdentifier(taxIdentifier)
    }

    override fun createAccount(account: Account): Account {
        return accountRepository.save(account.toModel()).toDomain()
    }
}