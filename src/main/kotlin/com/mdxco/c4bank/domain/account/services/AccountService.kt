package com.mdxco.c4bank.domain.account.services

import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.exceptions.AccountAlreadyExists
import com.mdxco.c4bank.domain.account.gateways.AccountGateway
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountGateway: AccountGateway,
) {
    fun checkIfAccountIsCreated(taxIdentifier: String): Boolean {
        return accountGateway.checkIfAccountIsCreated(taxIdentifier)
    }

    @Transactional
    fun createAccount(account: Account): Account {
        if (checkIfAccountIsCreated(account.taxIdentifier)) {
            throw AccountAlreadyExists(account.taxIdentifier)
        }

        return accountGateway.createAccount(account)
    }
}