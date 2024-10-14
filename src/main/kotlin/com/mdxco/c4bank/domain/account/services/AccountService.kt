package com.mdxco.c4bank.domain.account.services

import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.entities.AccountUpdate
import com.mdxco.c4bank.domain.account.exceptions.AccountAlreadyExistsException
import com.mdxco.c4bank.domain.account.gateways.AccountGateway
import com.mdxco.c4bank.domain.account.helpers.AccountHelpers
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountGateway: AccountGateway,
    private val accountHelpers: AccountHelpers
) {
    fun getAccount(id: String): Account? {
        return accountGateway.getById(id)
    }

    @Transactional
    fun createAccount(account: Account): Account {
        if (accountHelpers.isAccountCreated(account.taxIdentifier)) {
            throw AccountAlreadyExistsException()
        }

        val accountWithAccountNumber = account.copy(accountNumber = accountHelpers.generateNextAccountNumber())
        val accountCreated = accountGateway.saveAccount(accountWithAccountNumber)

        return accountCreated
    }

    @Transactional
    fun updateAccount(accountFoundById: Account, accountUpdates: AccountUpdate): Account {
        val updatedAccount = accountGateway.saveAccount(
            accountFoundById.copy(
                address = accountUpdates.address ?: accountFoundById.address,
                phone = accountUpdates.phone ?: accountFoundById.phone
            )
        )

        return updatedAccount
    }
}