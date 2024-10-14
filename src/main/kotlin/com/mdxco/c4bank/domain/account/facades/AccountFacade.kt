package com.mdxco.c4bank.domain.account.facades

import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.entities.AccountUpdate
import com.mdxco.c4bank.domain.account.exceptions.AccountNotFoundException
import com.mdxco.c4bank.domain.account.exceptions.InsufficientDataToUpdateException
import com.mdxco.c4bank.domain.account.services.AccountService
import com.mdxco.c4bank.domain.account.services.AddressService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountFacade(
    private val accountService: AccountService,
    private val addressService: AddressService
) {
    // need to lock due to concurrency in extreme cases (like multiple users trying to sign up)
    // preventing from duplicated account numbers
    // private val lock = ReentrantLock()

    @Transactional
    fun createAccount(account: Account): Account {
        val address = addressService.addAddress(account.address)
        val accountCreated = accountService.createAccount(account.copy(address = address))
        return accountCreated
    }

    @Transactional
    fun updateAccount(accountId: String, accountUpdates: AccountUpdate): Account {
        if (accountUpdates.address?.isNullOrBlank() != false && accountUpdates.phone.isNullOrBlank()) {
            throw InsufficientDataToUpdateException()
        }

        val accountFoundById = accountService.getAccount(accountId) ?: throw AccountNotFoundException()
        val addressUpdated = addressService.updateAddress(accountFoundById.address, accountUpdates.address)

        return accountService.updateAccount(accountFoundById, accountUpdates.copy(address = addressUpdated))
    }

    @Transactional
    fun deactivateAccount(accountId: String): Account {
        return accountService.deactivateAccount(accountId)
    }

    @Transactional
    fun reactivateAccount(accountId: String): Account {
        return accountService.reactivateAccount(accountId)
    }
}