package com.mdxco.c4bank.domain.account.services

import com.mdxco.c4bank.domain.account.entities.Account
import com.mdxco.c4bank.domain.account.exceptions.AccountAlreadyExists
import com.mdxco.c4bank.domain.account.gateways.AccountGateway
import com.mdxco.c4bank.domain.account.helpers.AccountHelpers
import java.util.concurrent.locks.ReentrantLock
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountGateway: AccountGateway,
    private val accountHelpers: AccountHelpers
) {
    // need to lock due to concurrency in extreme cases (like multiple users trying to sign up)
    // preventing from duplicated account numbers
    private val lock = ReentrantLock()

    @Transactional
    fun createAccount(account: Account): Account {
        lock.lock()

        try {
            if (accountHelpers.checkIfAccountIsCreated(account.taxIdentifier)) {
                throw AccountAlreadyExists(account.taxIdentifier)
            }

            return accountGateway.createAccount(account.copy(accountNumber = accountHelpers.generateNextAccountNumber()))
        } finally {
            lock.unlock()
        }
    }
}