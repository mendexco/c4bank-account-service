package com.mdxco.c4bank.account.infrastructure.gateways.account

import com.mdxco.c4bank.account.domain.account.entities.Account
import com.mdxco.c4bank.account.domain.account.gateways.AccountGateway
import com.mdxco.c4bank.account.infrastructure.jpa.repositories.AccountRepository
import com.mdxco.c4bank.account.infrastructure.models.account.toModel
import org.springframework.stereotype.Component

@Component
class AccountGatewayImpl(
    private val accountRepository: AccountRepository
) : AccountGateway {
    override fun checkIfAccountIsCreated(taxIdentifier: String): Boolean {
        return accountRepository.existsByTaxIdentifier(taxIdentifier)
    }

    override fun checkIfAddressIsInUse(addressId: String): Boolean {
        return accountRepository.existsByAddressId(addressId)
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