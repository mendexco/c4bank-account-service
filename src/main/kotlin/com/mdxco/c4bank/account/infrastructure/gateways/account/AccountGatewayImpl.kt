package com.mdxco.c4bank.account.infrastructure.gateways.account

import com.mdxco.c4bank.account.domain.account.AccountGateway
import com.mdxco.c4bank.account.domain.account.entities.Account
import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier
import com.mdxco.c4bank.account.infrastructure.jpa.repositories.AccountRepository
import com.mdxco.c4bank.account.infrastructure.models.account.toModel
import org.springframework.stereotype.Component

@Component
class AccountGatewayImpl(
    private val accountRepository: AccountRepository,
) : AccountGateway {
    override fun checkIfAccountIsCreated(taxIdentifier: TaxIdentifier): Boolean = accountRepository.existsByTaxIdentifier(taxIdentifier)

    override fun getLatestAccountNumber(): String? = accountRepository.findTopByOrderByIdDesc()?.accountNumber

    override fun getById(id: String): Account? = accountRepository.findById(id).orElse(null)?.toDomain()

    override fun saveAccount(account: Account): Account = accountRepository.save(account.toModel()).toDomain()
}
