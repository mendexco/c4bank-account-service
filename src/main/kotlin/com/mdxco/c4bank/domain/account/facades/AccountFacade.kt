package com.mdxco.c4bank.domain.account.facades

import com.mdxco.c4bank.commons.utils.LockingHelpers
import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.entities.AccountUpdate
import com.mdxco.c4bank.domain.account.exceptions.AccountNotFoundException
import com.mdxco.c4bank.domain.account.exceptions.InsufficientDataToUpdateException
import com.mdxco.c4bank.domain.account.services.AccountService
import com.mdxco.c4bank.domain.account.services.AddressService
import org.springframework.stereotype.Service

@Service
class AccountFacade(
    private val accountService: AccountService,
    private val addressService: AddressService
) {
    fun createAccount(account: Account): Account {
        return LockingHelpers.withLock {
            val address = addressService.addAddress(account.address)
            val accountCreated = accountService.createAccount(account.copy(address = address))
            return@withLock accountCreated
        }
    }

    fun updateAccount(accountId: String, accountUpdates: AccountUpdate): Account {
        if (accountUpdates.address?.isNullOrBlank() != false && accountUpdates.phone.isNullOrBlank()) {
            throw InsufficientDataToUpdateException()
        }

        return LockingHelpers.withLock {
            val accountFoundById = accountService.getAccount(accountId) ?: throw AccountNotFoundException()
            val addressUpdated = addressService.updateAddress(accountFoundById.address, accountUpdates.address)

            return@withLock accountService.updateAccount(
                accountFoundById,
                accountUpdates.copy(address = addressUpdated)
            )
        }
    }

    fun deactivateAccount(accountId: String): Account {
        return accountService.deactivateAccount(accountId)
    }

    fun reactivateAccount(accountId: String): Account {
        return accountService.reactivateAccount(accountId)
    }
}